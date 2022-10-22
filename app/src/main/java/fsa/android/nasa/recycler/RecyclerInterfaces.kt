package fsa.android.nasa.recycler

fun interface AddItem {
    fun add(position:Int)
}

fun interface RemoveItem {
    fun remove(position:Int)
}

fun interface MoveUpItem {
    fun moveUp(position:Int)
}

fun interface MoveDownItem {
    fun moveDown(position:Int)
}