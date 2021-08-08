function addLoadEvent(func) {
    let oldonload = window.onload;
    if (typeof oldonload != 'function') {
        window.onload = func;
    } else {
        window.onload = function () {
            oldonload();
            func();
        }
    }
}

function resizeBGVideo() {
    let elem = document.querySelector(".bg-video>.video-01");
    let fa = document.querySelector(".bg-video");
    if (elem == null || fa == null) return;
    if (elem.clientWidth < fa.clientWidth) {
        elem.style.width = "100%";
        elem.style.heigh = "auto";
        return;
    }
    if (elem.clientHeight < fa.clientHeight) {
        elem.style.width = "auto";
        elem.style.heigh = "100%";
        return;
    }
}


const myObserver = new ResizeObserver(entries => {
    entries.forEach(entry => {
        console.log('大小位置', entry.contentRect)
        console.log('监听的DOM', entry.target)
    })
    resizeBGVideo();
});
myObserver.observe(document.body);

