package com.example.switchviewtyperecycleview

class Flower(var resourceImage: Int) {
    private var typeDisplay = 0


    companion object {
        const val TYPE_GRID = 1
        const val TYPE_LIST = 2
        const val TYPE_STAGGERED = 3
    }

    fun getTypeDisplay(): Int {
        return typeDisplay
    }

    fun setTypeDisplay(typeDisplay: Int) {
        this.typeDisplay = typeDisplay
    }
}
