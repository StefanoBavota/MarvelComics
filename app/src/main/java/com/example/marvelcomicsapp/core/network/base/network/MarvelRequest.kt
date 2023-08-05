package com.example.marvelcomicsapp.core.network.base.network

import com.google.gson.annotations.SerializedName

class MarvelRequest (
    @SerializedName("offset")
    val offset: String?,
    @SerializedName("marvelId")
    val marvelId: Int?,
    @SerializedName("producerId")
    val producerId: Int?,
    @SerializedName("marvelType")
    val marvelType: Int?,
    @SerializedName("offset")
    val includeImages: Boolean?,
    @SerializedName("count")
    val count: String?,
    @SerializedName("actorId")
    val actorId: Int?,
    @SerializedName("genreId")
    val genreId: Int?,
    @SerializedName("includeDownloads")
    val includeDownloads: Boolean?,
)
