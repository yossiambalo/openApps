/*
      Odysii 2018
      -----------
      Last Updated: 26.07.18
      Author: Omri Moshe,  omri.moshe@odysii.com
      Updated by: Hagay Levy, hagay.levy@odysii.com
      Description: Base script to provide base behavior for sample apps
      **Requires iPumpApi to be loaded first
      *
      * version: 1.1
*/
var baseJS = new function() {
  var self = this;
  var heartbeatRequestInterval = null;
  var softKeyCBArray = new Array(8);

  var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  var days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

  self.debug = false;

  var handleKey = function(keyId) {
    var key = parseInt(keyId);
    if (key > 0 && key <= softKeyCBArray.length && softKeyCBArray != null) {
      var callBack = softKeyCBArray[ key - 1 ];
      if (callBack)
        callBack();
    }
  };

  self.formatDateTime = function(d){
    var day = days[d.getDay()];

    var hr = d.getHours();
    var min = d.getMinutes();
    if (min < 10) {
      min = "0" + min;
    }
    var ampm = "am";
    if( hr > 12 ) {
      hr -= 12;
      ampm = "pm";
    }

    var sec = d.getSeconds();
    if (sec < 10) {
      sec = "0" + sec;
    }

    var date = d.getDate();
    var month = months[d.getMonth()];
    var year = d.getFullYear();
    return day + " " + hr + ":" + min + ":" + sec + ampm + " " + date + " " + month + " " + year;
  }

  self.init = function(name, doneCallBack, noHeartBeat) {

    self.appName = name;
    self.appId = self.getParameterByName("loginId", "1011");
    self.port = self.getParameterByName("port", "7200");

    //Update iPumpAPI
    iPumpAPI.clientId = self.appId;
    iPumpAPI.appName = self.appName;
    iPumpAPI.port = self.port;

    self.connectFunction(doneCallBack, noHeartBeat);
  };

  self.connectFunction = function(doneCallBack, noHeartBeat){
    iPumpAPI.connect(function () {}, null, function () {
        self.logMsg("connected");
        iPumpAPI.login(function () {
            self.logMsg("logged in");

            if(noHeartBeat != true) {
              heartbeatRequestInterval = setInterval(function () {
                self.sendHeartbeat();
              }, 3000);
            }

            iPumpAPI.registerForSoftKeys(function (softkey) {
                self.logMsg("got softkey: " + softkey);
                handleKey(softkey);
              },
              function () {
                self.errorMsg("got softkey failed.");
              }
            );
            if (doneCallBack)
              doneCallBack();
          },
          function () {
            self.errorMsg("login failed!");
          }
        );
      },
      function () {
        self.errorMsg("connect failed!")
      }
    );
  }

  self.clearAllSoftKeysCB = function(){
    softKeyCBArray = new Array(8);
  }

  self.setSoftKeyCB = function(softKey, callback) {
    if ( softKey != null && softKey.constructor === Number && softKey > 0 && softKey <= softKeyCBArray.length) {
      softKeyCBArray[softKey - 1] = callback;
    }
  };

  self.logMsg = function(msg) {
    console.log(self.appName + " -loginId: " + self.appId + " :: " + msg);
  };

  self.errorMsg = function(msg) {
    self.logMsg(" ERROR : " + JSON.stringify(msg));
  };

  self.sendHeartbeat = function() {
    var now = new Date();
    if (self.debug) {
      self.logMsg("[Heartbeat] - " + now.toLocaleString());
    }
    iPumpAPI.sendHeartbeat(null, function (errorString) {
      self.logMsg("error on hearbeat rquest - " + errorString + " aborting interval.");
      self.stopHeartbeat();
    });
  };

  self.getParameterByName = function(name, defaultVal, url) {
    if (!url) url = window.location.href;
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
      results = regex.exec(url);
    if (!results) return defaultVal;
    if (!results[2]) return '';
    var retVal = decodeURIComponent(results[2].replace(/\+/g, " "));
    return retVal!=null?retVal:defaultVal;
  }

  self.setAppStyle = function(cssPath) {
    var cssId = self.appName + "_Style";
    var cssElem = document.getElementById(cssId);
    if (!cssElem) {
      self.addStyle(cssPath, cssId);
    } else {
      cssElem.href = cssPath;
    }
  }

  self.replaceStyle = function(cssElementId, cssPath){
    var cssElem = document.getElementById(cssElementId);
    if( cssElem ) {
      cssElem.href = cssPath;
    }
  }

  self.addStyle = function(cssElementId, cssPath){
    var cssElem = document.getElementById(cssElementId);
    if( cssElem ){
      self.replaceStyle(cssElementId, cssPath);
    }else {
      var head = document.getElementsByTagName('head')[0];
      var link = document.createElement('link');
      link.rel = 'stylesheet';
      link.type = 'text/css';
      link.href = cssPath;
      link.media = 'all';
      link.id = cssElementId;
      head.appendChild(link);
    }
  }

  self.stopHeartbeat = function() {
    if(heartbeatRequestInterval != null) {
      clearInterval(heartbeatRequestInterval);
    }
  }

  self.getAvailableKeys = function(layoutName, frame) {
    switch(layoutName){
      case "layout104_1":
        return 8;
      case "layout156_1":
        return 8;
      case "layout156_2":
      case "layout156_3":
        if(frame.location != 1) {
          return 1;
        }else{
          return 6;
        }
      case "layout156_4":
        if(frame.location != 2 && frame.location != 1) {
          return 1;
        }else{
          if(frame.location == 2){
            return 0;
          }else{
            return 6;
          }
        }
    }
    return 0;
  }

  self.getAvailableKeysArry = function(layoutName, frame) {

    switch(layoutName){
      case "layout104_1":
        return [1,2,3,4,5,6,7,8];
      case "layout156_1":
        return [1,2,3,4,5,6,7,8];
      case "layout156_2":
      case "layout156_3":
        if(frame.location == 1) {
          return [1,2,3,4,5,6];
        }

        if(frame.location == 2){
          return [7];
        }

        if(frame.location == 3){
          return [8];
        }

      case "layout156_4":
        if( frame.location == 1 ){
          return [1,2,3, 4,5,6];
        }

        if(frame.location == 3){
          return [7];
        }

        if(frame.location == 4){
          return [8];
        }

        if(frame.location == 2){
          return [0];
        }
    }
    return [0];
  }

  self.getStyleUponResolution = function(layoutName) {
    if(layoutName.indexOf("layout156") == -1){
      return "common/css/4on3_style.css";
    }else{
      return "common/css/16on9_style.css";
    }
  }

  self.getSoftKeysStyleForMainScreen = function(layoutName, template) {
    return "common/css/" + layoutName + "_t_" + template + ".css";
  }

  self.registerForAppConfigChange = function( callback ) {

    iPumpAPI.registerForAppConfigChange (
      function(resData) {
        if (resData == null){
          return;
        }

        if(resData.layoutName == ""){
          return;
        }

        self.addStyle("app_style", self.getStyleUponResolution(resData.layoutName));
        self.addStyle("sk_template", self.getSoftKeysStyleForMainScreen(resData.layoutName, 1));
        self.setAppUponframe(resData.frame.location, resData.layoutName);
        if(callback) {
          callback(resData);
        }
      },
      function(errorString) {
        baseJS.errorMsg(errorString);
      }
    );
  }

  self.hideAllDivs = function(){
    var dispElement = document.getElementById("mainScreen");
    if(dispElement != null){
      dispElement.style.visibility = "collapse";
    }

    dispElement = document.getElementById("oneSoftkeyApp");
    if(dispElement != null){
      dispElement.style.visibility = "collapse";
    }

    dispElement = document.getElementById("noSoftKeyApp");
    if(dispElement != null){
      dispElement.style.visibility = "collapse";
    }
  }

  self.setAppUponframe = function(frameIndex, layoutName) {
    var elementId = "noSoftKeyApp";
    self.hideAllDivs();

    switch(frameIndex){
      case 0:
      case 1:
        elementId = "mainScreen";
        break;
      case 2:
        if(layoutName=="layout156_4"){
          elementId = "noSoftKeyApp";
        }
        else{
          elementId = "oneSoftkeyApp";
        }
        break;
      case 3:
      case 4:
        elementId = "oneSoftkeyApp";
        break;
    }

    var dispElement = document.getElementById(elementId);
    if(dispElement != null){
      dispElement.style.visibility = "visible";
    }
  }

}
