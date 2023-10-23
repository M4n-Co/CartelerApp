package com.example.cartelerapp.home.movieDetail

import android.os.Parcel
import android.os.Parcelable

class EntrenamientoInfo(
    val _id: String?,
    val descripcion: String?,
    val descripcionCorta: String?,
    val grupoMuscular: String?,
    val id: String?,
    val instrucciones: String?,
    val mediaSource: String?,
    val nivel: String?,
    val nombre: String?,
    val numeroEjercicios: Int,
    val numeroSeries: Int,
    val objetivo: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(descripcion)
        parcel.writeString(descripcionCorta)
        parcel.writeString(grupoMuscular)
        parcel.writeString(id)
        parcel.writeString(instrucciones)
        parcel.writeString(mediaSource)
        parcel.writeString(nivel)
        parcel.writeString(nombre)
        parcel.writeInt(numeroEjercicios)
        parcel.writeInt(numeroSeries)
        parcel.writeString(objetivo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EntrenamientoInfo> {
        override fun createFromParcel(parcel: Parcel): EntrenamientoInfo {
            return EntrenamientoInfo(parcel)
        }

        override fun newArray(size: Int): Array<EntrenamientoInfo?> {
            return arrayOfNulls(size)
        }
    }
}