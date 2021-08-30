/*
 * Modified Dp.kt
 *
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
@file:Suppress("NOTHING_TO_INLINE")

package com.blundell.deeplydippy

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.geometry.isSpecified
import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.blundell.deeplydippy.Dip.Companion.Hairline

//import androidx.compose.ui.util.packFloats
//import androidx.compose.ui.util.unpackFloat1
//import androidx.compose.ui.util.unpackFloat2
//import kotlin.math.max
//import kotlin.math.min

/**
 * Dimension value representing device-independent pixels (dip). Component APIs specify their
 * dimensions such as line thickness in DP with Dp objects. Hairline (1 pixel) thickness
 * may be specified with [Hairline], a dimension that take up no space. Dip are normally
 * defined using [dip], which can be applied to [Int], [Double], and [Float].
 *     val leftMargin = 10.dip
 *     val rightMargin = 10f.dip
 *     val topMargin = 20.0.dip
 *     val bottomMargin = 10.dip
 * Drawing is done in pixels. To retrieve the pixel size of a Dip, use [toPx]:
 *     val lineThicknessPx = lineThickness.toPx(context)
 * [toPx] is normally needed only for painting operations.
 */
@Suppress("INLINE_CLASS_DEPRECATED", "EXPERIMENTAL_FEATURE_WARNING")
@Immutable
inline class Dip(val value: Float) : Comparable<Dip> {
    /**
     * Add two [Dip]s together.
     */
    @Stable
    inline operator fun plus(other: Dip) =
        Dp(value = this.value + other.value)

    /**
     * Add a [Dip] & a [Dp] together.
     */
    @Stable
    inline operator fun plus(other: Dp) =
        Dp(value = this.value + other.value)

    /**
     * Subtract a [Dip] from another one.
     */
    @Stable
    inline operator fun minus(other: Dip) =
        Dp(value = this.value - other.value)

    /**
     * Subtract a [Dp] from a [Dip].
     */
    @Stable
    inline operator fun minus(other: Dp) =
        Dp(value = this.value - other.value)

    /**
     * This is the same as multiplying the [Dip] by -1.0.
     */
    @Stable
    inline operator fun unaryMinus() = Dp(-value)

    /**
     * Divide a [Dip] by a scalar.
     */
    @Stable
    inline operator fun div(other: Float): Dp =
        Dp(value = value / other)

    @Stable
    inline operator fun div(other: Int): Dp =
        Dp(value = value / other)

    /**
     * Divide by another [Dip] to get a scalar.
     */
    @Stable
    inline operator fun div(other: Dip): Float = value / other.value

    /**
     * Divide by another [Dp] to get a scalar.
     */
    @Stable
    inline operator fun div(other: Dp): Float = value / other.value

    /**
     * Multiply a [Dp] by a scalar.
     */
    @Stable
    inline operator fun times(other: Dp): Dp =
        Dp(value = value * other.value)

    /**
     * Multiply a [Dp] by a [Dip] scalar.
     */
    @Stable
    inline operator fun times(other: Dip): Dp =
        Dp(value = value * other.value)

    @Stable
    inline operator fun times(other: Int): Dp =
        Dp(value = value * other)

    /**
     * Support comparing Dimensions with comparison operators.
     */
    @Stable
    override /* TODO: inline */ operator fun compareTo(other: Dip) = value.compareTo(other.value)

    @Stable
    override fun toString() = if (isUnspecified) "Dip.Unspecified" else "$value.dip"

    companion object {
        /**
         * A dimension used to represent a hairline drawing element. Hairline elements take up no
         * space, but will draw a single pixel, independent of the device's resolution and density.
         */
        @Stable
        val Hairline = Dp(value = 0f)

        /**
         * Infinite dip dimension.
         */
        @Stable
        val Infinity = Dp(value = Float.POSITIVE_INFINITY)

        /**
         * Constant that means unspecified Dip
         */
        @Stable
        val Unspecified = Dp(value = Float.NaN)
    }
}

/**
 * `false` when this is [Dip.Unspecified].
 */
@Stable
inline val Dip.isSpecified: Boolean
    get() = !value.isNaN()

/**
 * `true` when this is [Dip.Unspecified].
 */
@Stable
inline val Dip.isUnspecified: Boolean
    get() = value.isNaN()

/**
 * If this [Dip] [isSpecified] then this is returned, otherwise [block] is executed
 * and its result is returned.
 */
inline fun Dip.takeOrElse(block: () -> Dp): Dp =
    if (isSpecified) this.value.dp else block()

/**
 * Create a [Dip] using an [Int]:
 *     val left = 10
 *     val x = left.dp
 *     // -- or --
 *     val y = 10.dp
 */
@Stable
inline val Int.dip: Dp
    get() = Dp(value = this.toFloat())

/**
 * Create a [Dip] using a [Double]:
 *     val left = 10.0
 *     val x = left.dp
 *     // -- or --
 *     val y = 10.0.dp
 */
@Stable
inline val Double.dip: Dp
    get() = Dp(value = this.toFloat())

/**
 * Create a [Dip] using a [Float]:
 *     val left = 10f
 *     val x = left.dp
 *     // -- or --
 *     val y = 10f.dp
 */
@Stable
inline val Float.dip: Dp
    get() = Dp(value = this)

/**
 * Ensures that this value lies in the specified range [minimumValue]..[maximumValue].
 *
 * @return this value if it's in the range, or [minimumValue] if this value is less than
 * [minimumValue], or [maximumValue] if this value is greater than [maximumValue].
 */
@Stable
inline fun Dip.coerceIn(minimumValue: Dip, maximumValue: Dip): Dp =
    Dp(value = value.coerceIn(minimumValue.value, maximumValue.value))

/**
 * Ensures that this value is not less than the specified [minimumValue].
 * @return this value if it's greater than or equal to the [minimumValue] or the
 * [minimumValue] otherwise.
 */
@Stable
inline fun Dip.coerceAtLeast(minimumValue: Dip): Dp =
    Dp(value = value.coerceAtLeast(minimumValue.value))

/**
 * Ensures that this value is not greater than the specified [maximumValue].
 *
 * @return this value if it's less than or equal to the [maximumValue] or the
 * [maximumValue] otherwise.
 */
@Stable
inline fun Dip.coerceAtMost(maximumValue: Dip): Dp =
    Dp(value = value.coerceAtMost(maximumValue.value))

/**
 *
 * Return `true` when it is finite or `false` when it is [Dip.Infinity]
 */
@Stable
inline val Dip.isFinite: Boolean
    get() = value != Float.POSITIVE_INFINITY

///**
// * Linearly interpolate between two [Dip]s.
// *
// * The [fraction] argument represents position on the timeline, with 0.0 meaning
// * that the interpolation has not started, returning [start] (or something
// * equivalent to [start]), 1.0 meaning that the interpolation has finished,
// * returning [stop] (or something equivalent to [stop]), and values in between
// * meaning that the interpolation is at the relevant point on the timeline
// * between [start] and [stop]. The interpolation can be extrapolated beyond 0.0 and
// * 1.0, so negative values and values greater than 1.0 are valid.
// */
//@Stable
//fun lerp(start: Dp, stop: Dp, fraction: Float): Dp {
//    return Dp(lerp(start.value, stop.value, fraction))
//}

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
// Structures using Dp
// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

///**
// * Constructs a [DipOffset] from [x] and [y] position [Dip] values.
// */
//@Stable
//fun DipOffset(x: Dip, y: Dip): DipOffset = DipOffset(packFloats(x.value, y.value))
//
///**
// * A two-dimensional offset using [Dip] for units
// */
//@OptIn(ExperimentalUnsignedTypes::class)
//@Suppress("INLINE_CLASS_DEPRECATED", "EXPERIMENTAL_FEATURE_WARNING")
//@Immutable
//inline class DipOffset internal constructor(@PublishedApi internal val packedValue: Long) {
//
//    /**
//     * The horizontal aspect of the offset in [Dip]
//     */
//    @Stable
//            /*inline*/ val x: Dip
//        get() = unpackFloat1(packedValue).dp
//
//    /**
//     * The vertical aspect of the offset in [Dip]
//     */
//    @Stable
//            /*inline*/ val y: Dip
//        get() = unpackFloat2(packedValue).dp
//
//    /**
//     * Returns a copy of this [DipOffset] instance optionally overriding the
//     * x or y parameter
//     */
//    fun copy(x: Dip = this.x, y: Dip = this.y): DipOffset = DipOffset(x, y)
//
//    /**
//     * Subtract a [DipOffset] from another one.
//     */
//    @Stable
//    inline operator fun minus(other: DipOffset) =
//        DipOffset(x - other.x, y - other.y)
//
//    /**
//     * Add a [DipOffset] to another one.
//     */
//    @Stable
//    inline operator fun plus(other: DipOffset) =
//        DipOffset(x + other.x, y + other.y)
//
//    @Stable
//    override fun toString(): String = "($x, $y)"
//
//    companion object {
//        /**
//         * A [DipOffset] with 0 DP [x] and 0 DP [y] values.
//         */
//        val Zero = DipOffset(0.dip, 0.dip)
//    }
//}

///**
// * Linearly interpolate between two [DipOffset]s.
// *
// * The [fraction] argument represents position on the timeline, with 0.0 meaning
// * that the interpolation has not started, returning [start] (or something
// * equivalent to [start]), 1.0 meaning that the interpolation has finished,
// * returning [stop] (or something equivalent to [stop]), and values in between
// * meaning that the interpolation is at the relevant point on the timeline
// * between [start] and [stop]. The interpolation can be extrapolated beyond 0.0 and
// * 1.0, so negative values and values greater than 1.0 are valid.
// */
//@Stable
//fun lerp(start: DpOffset, stop: DpOffset, fraction: Float): DpOffset =
//    DpOffset(lerp(start.x, stop.x, fraction), lerp(start.y, stop.y, fraction))

/**
 * A four dimensional bounds using [Dip] for units
 */
@Immutable
data class DipRect(
    @Stable
    val left: Dip,
    @Stable
    val top: Dip,
    @Stable
    val right: Dip,
    @Stable
    val bottom: Dip
) {
    companion object
}

/**
 * A width of this Bounds in [Dip].
 */
@Stable
inline val DipRect.width: Dp
    get() = right - left

/**
 * A height of this Bounds in [Dip].
 */
@Stable
inline val DipRect.height: Dp
    get() = bottom - top
