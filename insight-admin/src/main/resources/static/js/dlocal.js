function addFieldsScript(callback) {
    var urlParams = new URLSearchParams(window.location.search);
    let env = urlParams.has('env') ? urlParams.get('env') : "local";
    let src = getEnvironmentUrl(env);
    addScript(src, callback);
}


function addScript(src, callback) {
    var s = document.createElement('script');
    s.setAttribute('src', src);
    s.onload = callback;
    document.body.appendChild(s);
}

function addStyle(href) {
    var linkElement = document.createElement('link');
    linkElement.setAttribute('rel', 'stylesheet');
    linkElement.setAttribute('type', 'text/css');
    linkElement.setAttribute('href', href)
    document.body.appendChild(linkElement);
}

function getEnvironmentUrl(env) {
    let src = "";
//    switch (env) {
//        case "local":
//            src = "/dlocal.js";
//            break;
//        case "rc":
//            src = "http://backoffice.dlocal.rc/fields/dlocal.js";
//            break;
//        case "sbx":
//            src = "https://js-sandbox.dlocal.com";
//            break;
//        case "prod":
//            src = "https://js.dlocal.com/";
//            break;
//    }
//    src = "https://js-sandbox.dlocal.com";
    src = "https://js.dlocal.com/";
    return src;
}

function getEnvironmentApiKey() {
    var urlParams = new URLSearchParams(window.location.search);
    let env = urlParams.has('env') ? urlParams.get('env') : "local";
    let key = urlParams.has('key') ? urlParams.get('key') : null;
//    if (!key) {
//        switch (env) {
//            case "local":
//                key = "efba0f53-252d-4da2-806c-3694a1e2d8";
//                break;
//            case "rc":
//                key = "efba0f53-252d-4da2-806c-3694a1e2d8";
//                break;
//            case "sbx":
//                key = "efba0f53-252d-4da2-806c-3cb6a1e2d8";
//                break;
//            case "prod":
//                key = "6cc70ec4-d814-4f71-9adb-c329462c93";
//                break;
//        }
//    }
//    key = "40fabf5a-2f5b-444e-a6d4-b00d5e99d50e";
    key = "8ad43b58-8426-4368-8d93-84bfee30076c";//live
    return key;
}