package com.yuresko.lenta.data.models

data class ResponseGifv(
    val result: Result
)

data class Result(
    val items: List<Items>
)

data class Items(
    val type: String,
    val data: Data
)

data class Data(
    val id: String,
    val type: String,
    val author: Author,
    val subsite: SubSite,
    val blocks: List<Blocks>?,
    val counters: Counters
)

data class Author(
    val id: String,
    val name: String,
    val description: String,
    val avatar: Avatar,
    val cover: Cover
)

data class Counters(
    val comments: Int,
    val favorites: Int
)

data class Cover(
    val type: String,
    val data: DataCover
)

data class DataCover(
    val uuid: String
)

data class Blocks(
    val type: String,
    val data: DataBlocks?
)

data class DataBlocks(
    val items: List<ItemBlocks>?
)

data class ItemBlocks(
    val image: ImageBlocks?
)

data class ImageBlocks(
    val data: ImageGifData
)

data class ImageGifData(
    val uuid: String?
)

data class SubSite(
    val id: String,
    val name: String,
    val description: String,
    val avatar: AvatarSubSite
)

data class AvatarSubSite(
    val type: String,
    val data: DataAvatarSubSite
)

data class DataAvatarSubSite(
    val uuid: String,
    val type: String
)

data class Avatar(
    val type: String,
    val data: DataAvatar
)

data class DataAvatar(
    val uuid: String,
    val type: String
)
