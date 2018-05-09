package br.com.fielddiary.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.fielddiary.model.Growth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HomeViewModel : ViewModel() {

    private val growths: MutableLiveData<List<Growth>?> = MutableLiveData()
    private val error: MutableLiveData<Exception?> = MutableLiveData()

    private val user = FirebaseAuth.getInstance().currentUser!!
    private val dbRef = FirebaseDatabase.getInstance().getReference("growths/${user.uid}")


    init {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                p0?.let { error.postValue(it.toException()) }
            }

            override fun onDataChange(p0: DataSnapshot?) {
                p0?.let {

                    val list = ArrayList<Growth>()
                    it.children.forEach { child -> list.add(child.getValue(Growth::class.java)!!) }

                    growths.postValue(list)
                }
            }
        })
    }

    fun getGrowths(): LiveData<List<Growth>?> {
        return growths
    }

    fun getError(): LiveData<Exception?> {
        return error
    }

    fun addGrowth(growth: Growth) {
        growth.id = dbRef.push().key
        val update = HashMap<String, Any>()
        update[growth.id!!] = growth
        dbRef.updateChildren(update).addOnFailureListener {
            error.postValue(it)
        }

    }

}