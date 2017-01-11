var ajax = {};
ajax.createXMLHttpRequest = function () {
    if (typeof XMLHttpRequest !== 'undefined') {
        return new XMLHttpRequest();
    }
    var versions = [
        "MSXML2.XmlHttp.6.0",
        "MSXML2.XmlHttp.5.0",
        "MSXML2.XmlHttp.4.0",
        "MSXML2.XmlHttp.3.0",
        "MSXML2.XmlHttp.2.0",
        "Microsoft.XmlHttp"
    ];

    var xhr;
    for (var i = 0; i < versions.length; i++) {
        try {
            xhr = new ActiveXObject(versions[i]);
            break;
        } catch (e) {
        }
    }
    return xhr;
};

ajax.request = function (url, method, data, onSuccess, onFailure){
        var xhr = ajax.createXMLHttpRequest();

        xhr.onreadystatechange = function () {
		if (xhr.readyState === 4 && xhr.status === 200){
			onSuccess(JSON.parse(xhr.responseText));
		} else if (xhr.readyState === 4) { // something went wrong but complete
			onFailure();
		}
	};
	xhr.open(method,url,true);
	xhr.send();
};

function receiveCurrentPosition(pos) {
	var crd = pos.coords;
        ajax.request('/haltestelle/' + crd.longitude + '/' + crd.latitude, 'GET', null, receiveHaltestelle, null);
};

function errorReceivingCurrentPosition(err) {
  console.warn('ERROR(' + err.code + '): ' + err.message);
};

function receiveHaltestelle(haltestelle)
{
	var url = 'http://iris.noncd.db.de/wbt/js/index.html?typ=cd&bhf=' + haltestelle.DS100;
	window.location.replace(url);
}

navigator.geolocation.getCurrentPosition(receiveCurrentPosition, errorReceivingCurrentPosition, { enableHighAccuracy: true, timeout: 30000, maximumAge: 120000});
