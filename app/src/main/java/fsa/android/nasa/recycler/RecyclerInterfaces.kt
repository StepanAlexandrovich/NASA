package fsa.android.nasa.recycler

// standard
fun interface AddItem {
    fun add(position:Int)
}
fun interface RemoveItem {
    fun remove(position:Int)
}
fun interface MoveItem {
    fun move(position:Int,direction:Int)
}

// extra
fun interface SwitchVisibleItem {
    fun switchVisible(position: Int)
}
   // смахивание item
interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}
interface ItemTouchHelperViewHolder {
    fun onItemSelected()
    fun onItemClear()
}

// HEADER EARTH MARS
interface ItemHeaderRealization{
    fun createMars(position:Int)
    fun createEarth(position:Int)
}
interface ItemEarthRealization:
    MoveItem
interface ItemMarsRealization:
    AddItem,
    RemoveItem,
    MoveItem,
    SwitchVisibleItem