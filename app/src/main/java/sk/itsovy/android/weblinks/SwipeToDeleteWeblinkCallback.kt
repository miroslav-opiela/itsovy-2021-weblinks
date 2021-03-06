package sk.itsovy.android.weblinks

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

// hlavna aktivita cez konstruktor tomuto callbacku da pristup k adapteru

class SwipeToDeleteWeblinkCallback(val adapter: WeblinksAdapter, val viewModel: WeblinkViewModel) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        // poviem view modelu a potom databaze nech sa vymazu data
        viewModel.delete(adapter.cachedWeblinks[position])
    }
}