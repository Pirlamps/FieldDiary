package br.com.fielddiary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Growth(
        var id: String? = null,
        var title: String? = null,
        var desc: String? = null
) : Parcelable