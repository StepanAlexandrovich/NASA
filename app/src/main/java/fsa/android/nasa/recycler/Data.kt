package fsa.android.nasa.recycler

const val TYPE_EARTH = 0
const val TYPE_MARS = 1
const val TYPE_HEADER = 2

data class Data(
    val type: Int = TYPE_EARTH,
    val name: String = "Text",
    val someDescription: String? = "Description"
)