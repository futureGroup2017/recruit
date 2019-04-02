function scrollPage(container, page, beforeMove) {
    var oContainer = $(container),
        width = oContainer.width(),
        height = oContainer.height(),
        index = 0,
        children = $(page),
        num = children.length
    oContainer.on('swipeUp', function () {
        index++
        if (index >= num)
            index = 0
        beforeMove(index)
    }).on('swipeDown', function () {
        index--
        if (index < 0)
            index = (num - 1)
        beforeMove(index)
    })
}
//
// $(".submit").on('click', function () {
//
// });