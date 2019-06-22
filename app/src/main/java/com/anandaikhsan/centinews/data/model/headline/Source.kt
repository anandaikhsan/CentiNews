package com.anandaikhsan.centinews.data.model.headline

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    val id: String?,
    val name: String?
): Parcelable