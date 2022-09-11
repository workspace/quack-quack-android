/*
 * Designed and developed by 2022 SungbinLand, Team Duckie
 *
 * [textfield.kt] created by EvergreenTree97 on 22. 8. 21. 오후 3:26
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/sungbinland/quack-quack/blob/main/LICENSE
 */

package team.duckie.quackquack.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import team.duckie.quackquack.common.npe
import team.duckie.quackquack.ui.constant.QuackHeight
import team.duckie.quackquack.ui.constant.QuackWidth
import team.duckie.quackquack.ui.modifier.applyQuackSize
import team.duckie.quackquack.ui.textstyle.QuackTextStyle

// /**
//  * QuackTextField 에서 표시할 텍스트의 [FontWeight] 에 따라 QuackTextField 의
//  * 높이가 달라집니다. 이를 계산하기 위해 사용됩니다.
//  *
//  * 만약 [FontWeight] 가 [FontWeight.Bold] 라면 TextField 에서 16dp 만큼 추가 높이를 갖고,
//  * 그렇지 않다면 18dp 만큼 추가 높이를 갖습니다.
//  *
//  * 좀 더 많은 경우를 신경쓰기 위해선 [FontWeight.Bold] 이상인 경우도 체크하면 좋지만,
//  * 현재 QuackTextField 의 디자인에는 최대 [FontWeight.Bold] 만 사용하고 있기 때문에
//  * [FontWeight.Bold] 만 체크합니다.
//  *
//  * @receiver [FontWeight] 가 [FontWeight.Bold] 인지 검사할 [QuackTextStyle]
//  * @return QuackTextField 에서 표시할 텍스트의 [FontWeight] 가 [FontWeight.Bold] 인지 여부
//  */
// private val QuackTextStyle.isBold get() = weight == FontWeight.Bold
//
// /**
//  * QuackTextField 밑에 표시될 ErrorText 와의 간격
//  */
// private val QuackTextFieldErrorTextTopPadding = 4.dp

/**
 * TextField 양옆에 추가되는 Decoration 들과의 간격
 *
 * ```
 * Decoration - TextField - Decoration
 * ```
 *
 * 에서 `-` 사이에 들어갈 간격 입니다.
 */
private val QuackTextFieldDecorationContentHorizontalPadding = 8.dp

// /**
//  * QuackTextField 에서 표시하는 텍스트의 [FontWeight] 가
//  * [FontWeight.Bold] 가 아닐 때 TextField 위에 들어갈 패딩
//  *
//  * QuackTextField 의 높이를 결정짓는 중요한 요소가 됩니다.
//  *
//  * **이 API 는 직접적으로 사용하면 안됩니다.**
//  * 대신에 [QuackTextStyle.calcQuackTextFieldTopPadding] 를 사용하세요.
//  *
//  * @see QuackTextStyle.isBold
//  */
// @DoNotUseDirectly
// private val QuackTextFieldTopPaddingWithNonBold = 18.dp

// /**
//  * QuackTextField 에서 표시하는 텍스트의 [FontWeight] 가
//  * [FontWeight.Bold] 일 때 TextField 위에 들어갈 패딩
//  *
//  * QuackTextField 의 높이를 결정짓는 중요한 요소가 됩니다.
//  *
//  * **이 API 는 직접적으로 사용하면 안됩니다.**
//  * 대신에 [QuackTextStyle.calcQuackTextFieldTopPadding] 를 사용하세요.
//  *
//  * @see QuackTextStyle.isBold
//  */
// @DoNotUseDirectly
// private val QuackTextFieldTopPaddingWithBold = 16.dp

// OptIn(DoNotUseDirectly::class)
// private fun QuackTextStyle.calcQuackTextFieldTopPadding() = when (isBold) {
//    true -> QuackTextFieldTopPaddingWithBold
//    else -> QuackTextFieldTopPaddingWithNonBold
// }

// /**
//  * QuackTextField 에 사용되는 TextField 의 하단에 들어갈 패딩
//  */
// private val QuackTextFieldBottomPadding = 8.dp

// /**
//  * QuackTextField 에 표시될 언더바의 높이
//  */
// private val QuackTextFieldUnderBarHeight = 1.dp

// /**
//  * QuackTextField 에서 사용할 아이템들의 색상을
//  * 계산하기 위한 유틸 함수들이 있는 클래스 입니다.
//  */
// @Immutable
// private object QuackTextFieldColors {
//     /**
//      * QuackTextField 에 표시될 텍스트의 색상을 계산합니다.
//      * placeholder 인지 여부에 따라 색상이 달라집니다.
//      *
//      * @param isPlaceholder placeholder 로 보여지고 있는지 여부
//      * @return QuackTextField 에 표시될 텍스트의 색상
//      */
//     @Stable
//     fun textColor(
//         isPlaceholder: Boolean,
//     ) = when (isPlaceholder) {
//         true -> QuackColor.Black
//         else -> QuackColor.Gray2
//     }
//
//     /**
//      * QuackTextField 에 표시될 후행 텍스트의 색상을 계산합니다.
//      * placeholder 인지 여부에 따라 색상이 달라집니다.
//      *
//      * @param isPlaceholder placeholder 로 보여지고 있는지 여부
//      * @return QuackTextField 에 표시될 후행 텍스트의 색상
//      */
//     @Stable
//     fun trailingTextColor(
//         isPlaceholder: Boolean = false,
//     ) = when (isPlaceholder) {
//         true -> QuackColor.DuckieOrange
//         else -> textColor(
//             isPlaceholder = false,
//         )
//     }
//
//     /**
//      * QuackTextField 의 후행으로 붙는 아이템의 색상을 정의합니다.
//      *
//      * QuackTextField 디자인에서 후행 아이템의 색상은
//      * placeholder 인 textColor 색상을 그대로 사용하므로
//      * [textColor] 를 그대로 델리게이트 합니다.
//      *
//      * QuackTextField 의 후행으로 올 수 있는 아이템은
//      * 아이콘 의외에 텍스트도 있으므로 Icon 이 아닌 Decorator
//      * 단어를 사용하였습니다.
//      */
//     val trailingDecoratorColor = textColor(
//         isPlaceholder = false,
//     )
//
//     /**
//      * QuackTextField 에 표시될 선행 아이콘의 색상을 계산합니다.
//      * placeholder 인지 여부에 따라 색상이 달라집니다.
//      *
//      * QuackTextField 디자인에서 선행 아이템의 색상은
//      * QuackTextField 의 텍스트 색상을 그대로 사용하므로
//      * [textColor] 를 그대로 델리게이트 합니다.
//      *
//      * @param isPlaceholder placeholder 로 보여지고 있는지 여부
//      * @return QuackTextField 에 표시될 선행 아이콘의 색상
//      */
//     @Stable
//     fun leadingIconColor(
//         isPlaceholder: Boolean,
//     ) = textColor(
//         isPlaceholder = isPlaceholder,
//     )
//
//     /**
//      * QuackTextField 에 표시될 언더바의 색상을 계산합니다.
//      * 현재 에러 상태인지에 따라 색상이 달라집니다.
//      *
//      * @param isError 현재 QuackTextField 가 에러 상태인지 여부
//      * @return QuackTextField 에 표시될 언더바의 색상
//      */
//     @Stable
//     fun underBarColor(
//         isError: Boolean,
//     ) = when (isError) {
//         true -> QuackColor.OrangeRed
//         else -> QuackColor.Gray3
//     }
//
//     /**
//      * QuackTextField 에서 사용할 커서 색상을 정의합니다.
//      * QuackTextField 는 커서의 색상으로 항상 [QuackColor.DuckieOrange]
//      * 를 사용합니다.
//      */
//     @Stable
//     val QuackTextFieldCursorColor = QuackColor.DuckieOrange
// }

@Composable
@NonRestartableComposable
fun QuackBasicTextField(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (text: String) -> Unit,
    textStyle: QuackTextStyle = QuackTextStyle.Body1,
    width: QuackWidth = QuackWidth.Fill,
    height: QuackHeight = QuackHeight.Wrap,
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        imeAction = ImeAction.Done,
    ),
    keyboardActions: KeyboardActions = KeyboardActions(),
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
) {
    var textFieldSize by remember {
        mutableStateOf(
            value = IntSize.Zero,
        )
    }

    BasicTextField(
        modifier = modifier
            .applyQuackSize(
                width = width,
                height = height,
            )
            .onPlaced { layoutCoordinates ->
                println("onPlaced: ${layoutCoordinates.size}")
                // size 에서 height 가 0으로 나옴
                textFieldSize = layoutCoordinates.size
            },
        value = text,
        onValueChange = onTextChanged,
        // TextField 는 애니메이션 적용 X
        textStyle = textStyle.asComposeStyle(),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        onTextLayout = { layoutResult ->
            // textFieldSize = layoutResult.size
            // size 에서 height 가 0으로 나옴
            println("onTextLayout: ${layoutResult.size}")
        },
        decorationBox = { textField ->
            QuackTextFieldDecorationBox(
                textFieldSize = textFieldSize,
                textField = textField,
                leadingContent = leadingContent,
                trailingContent = trailingContent,
            )
        },
    )
}

private const val QuackTextFieldLayoutId = "QuackTextFieldContent"
private const val QuackTextFieldLeadingContentLayoutId = "QuackTextFieldLeadingContent"
private const val QuackTextFieldTrailingContentLayoutId = "QuackTextFieldTrailingContent"

private fun Placeable.toDebugString() =
    "$width x $height / measured: $measuredWidth x $measuredHeight"

@Composable
private fun QuackTextFieldDecorationBox(
    textFieldSize: IntSize,
    textField: @Composable () -> Unit,
    leadingContent: @Composable (() -> Unit)?,
    trailingContent: @Composable (() -> Unit)?,
) {
    println("textFieldSize: $textFieldSize")
    val density = LocalDensity.current
    // QuackTextFieldDecorationContentHorizontalPadding 는 상수 값이라
    // remember 에 key 필요 없음
    val decorationItemGap = remember {
        with(
            receiver = density,
        ) {
            QuackTextFieldDecorationContentHorizontalPadding.roundToPx()
        }
    }

    Layout(
        modifier = Modifier
            .size(
                width = with(
                    receiver = density,
                ) {
                    textFieldSize.width.toDp().also {
                        println("textFieldSize.width.toDp(): $it")
                    }
                },
                height = with(
                    receiver = density,
                ) {
                    textFieldSize.height.toDp().also {
                        println("textFieldSize.height.toDp(): $it")
                    }
                },
            ),
        content = {
            if (leadingContent != null) {
                Box(
                    modifier = Modifier
                        .layoutId(
                            layoutId = QuackTextFieldLeadingContentLayoutId,
                        )
                        .wrapContentWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ) {
                    leadingContent()
                }
            }
            Box(
                // width 는 measure 하면서 지정
                modifier = Modifier
                    .layoutId(
                        layoutId = QuackTextFieldLayoutId,
                    )
                    .fillMaxHeight(),
                contentAlignment = Alignment.CenterStart,
            ) {
                textField()
            }
            if (trailingContent != null) {
                Box(
                    modifier = Modifier
                        .layoutId(
                            layoutId = QuackTextFieldTrailingContentLayoutId,
                        )
                        .wrapContentWidth()
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center,
                ) {
                    trailingContent()
                }
            }
        },
    ) { measurables, _ ->
        // 왜 measurePolicy 의 인자로 들어오는 constraints 가 Layout 의
        // constraints 와 다른지 모르겠음. 그래서 직접 Layout 의 constraints
        // 에 맞는 Constraints 를 만들어서 사용함.
        val constraints = Constraints.fixed(
            width = textFieldSize.width,
            height = textFieldSize.height,
        )
        println("constraints: $constraints")
        // leading content
        val leadingContentPlaceable = measurables.find { measurable ->
            measurable.layoutId == QuackTextFieldLeadingContentLayoutId
        }?.measure(
            constraints = constraints,
        )?.also {
            println("leadingContentPlaceable: ${it.toDebugString()}")
        }

        // trailing content
        val trailingContentPlaceable = measurables.find { measurable ->
            measurable.layoutId == QuackTextFieldTrailingContentLayoutId
        }?.measure(
            constraints = constraints,
        )?.also {
            println("trailingContentPlaceable: ${it.toDebugString()}")
        }

        // TextField
        val textFieldPlaceable = measurables.find { measurable ->
            measurable.layoutId == QuackTextFieldLayoutId
        }?.measure(
            constraints = Constraints.fixed(
                // TextField 의 가로 길이는 기본적으로 decoration 아이템들의 가로 길이를
                // 포함하고 있음. 만약 decoration 아이템이 존재한다면 TextField 의
                // 가로 길이를 decoration 아이템의 가로 길이만큼 줄여야 함.
                // 따라서 TextField 의 가로 길이에서 decoration 아이템의 가로 길이와
                // TextField 와 decoration 아이템 사이에 들어갈 간격 만큼 제외한 값으로
                // TextField 의 가로 길이로 설정함.
                width = constraints.minWidth.let { _width ->
                    var width = _width
                    if (leadingContentPlaceable != null) {
                        width -= leadingContentPlaceable.width + decorationItemGap
                    }
                    if (trailingContentPlaceable != null) {
                        width -= trailingContentPlaceable.width + decorationItemGap
                    }
                    width
                }.coerceAtLeast(
                    minimumValue = 0,
                ),
                height = constraints.minHeight,
            )
        )?.also {
            println("textFieldPlaceable: ${it.toDebugString()}")
        } ?: npe(
            lazyMessage = {
                notFoundRequiredLayoutIdForQuackTextFieldMessage(
                    layoutId = QuackTextFieldLayoutId,
                )
            },
        )

        layout(
            width = constraints.minWidth,
            height = constraints.minHeight,
        ) {
            val textFieldStartOffset = leadingContentPlaceable?.width?.plus(
                other = decorationItemGap,
            ) ?: 0

            leadingContentPlaceable?.place(
                x = 0,
                y = 0,
            )
            textFieldPlaceable.place(
                x = textFieldStartOffset.also {
                    println("textFieldStartOffset: $it")
                },
                y = 0,
            )
            trailingContentPlaceable?.place(
                x = (constraints.minWidth - trailingContentPlaceable.width).also {
                    println("trailingContentPlaceable x: $it")
                },
                y = 0,
            )
        }
    }
}

@Suppress("SameParameterValue")
private fun notFoundRequiredLayoutIdForQuackTextFieldMessage(
    layoutId: String,
) = """
    |QuackTextField 를 구성하는데 필요한 필수 layoutId 를 가진 컴포저블을
    |찾을 수 없습니다. 올바른 방법으로 QuackTextField 를 사용했는지 확인해 보세요.

    |필요한 layoutId: $layoutId
    """.trimMargin()