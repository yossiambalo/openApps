var tagId;
var divObj;
var appName = "appConfig";
var currentTemplateId = 1;
var layoutName = "";
var frameId = -1;

function updateData(isVisible, isFocus, state, layoutName, frameData, tagEnding)
{
        tagId = "stateIdEntry" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = state;
        }

        tagId = "layoutId" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = layoutName;
        }

        tagId = "frameLocIdEntry" + tagEnding;
        var divObjArr = document.getElementsByName(tagId);

        if(divObjArr != null) {
          for(var i = 0; i < divObjArr.length; i++){
            divObjArr[i].innerHTML = frameData.location;
          }
        }

        tagId = "xPosIdEntry" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = frameData.x;
        }

        tagId = "yPosIdEntry" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = frameData.y;
        }

        tagId = "widthIdEntry" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = frameData.width;
        }

        tagId = "heightIdEntry" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = frameData.height;
        }

        tagId = "softKeysEntry" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = frameData.softkeys!=null ? frameData.softkeys.toString() : "";
          }

        tagId = "visibleId" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = isVisible.toString();
        }

        tagId = "focusId" + tagEnding;
        divObj = document.getElementById(tagId);

        if(divObj != null) {
          divObj.innerHTML = isFocus.toString();
        }
}

function init()
{
  baseJS.registerForAppConfigChange(function(resData) {
    currentTemplateId = 1;
    layoutName = resData.layoutName;
    frameId = resData.frame.location;

    console.log("registerForAppConfigChange activated - " + layoutName);

    document.getElementById('verticalLayoutDiv').style.display="none";
    document.getElementById('horizontalLayoutDiv').style.display="none";
    document.getElementById('sideLayoutDiv').style.display="none";

    if(layoutName == "layout156_4" && frameId == 2){
      document.getElementById('horizontalLayoutDiv').style.display="";
      updateData(resData.isVisible, resData.isFocus, resData.state, resData.layoutName, resData.frame, "2");
    }

    else{
      if((layoutName == "layout156_4" && (frameId == 3  || frameId == 4)) || (layoutName == "layout156_3" && (frameId == 2  || frameId == 3)) || (layoutName == "layout156_2" && frameId == 2)){
        updateData(resData.isVisible, resData.isFocus, resData.state, resData.layoutName, resData.frame, "3");
        document.getElementById('sideLayoutDiv').style.display="";
      }

      else{
        updateData(resData.isVisible, resData.isFocus, resData.state, resData.layoutName, resData.frame, "");
        document.getElementById('verticalLayoutDiv').style.display="";
      }
    }

  });

  baseJS.init(appName,function(){});
}
