package com.github.begla.blockmania.data.blocks.plant

import com.github.begla.blockmania.blocks.Block.BLOCK_FORM
import com.github.begla.blockmania.blocks.Block.COLOR_SOURCE

/**
 * Tall grass variety 2 (higher number = taller)
 */
block {
    version = 1

    blockform = BLOCK_FORM.BILLBOARD
    colorsource = COLOR_SOURCE.FOLIAGE_LUT
    colorOffset = [0.7f, 0.7f, 0.7f, 1.0f]

    translucent = true
    penetrable = true
    
    waving = true
}