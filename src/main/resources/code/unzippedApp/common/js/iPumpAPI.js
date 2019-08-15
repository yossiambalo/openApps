//Odysii 2017
//-----------------------------------------
//iPump API - Singleton usage once included
//-----------------------------------------
//Last Updated: 13.06.18
//Description: This is an API between the application and iPump.
//Version: 4.0
//Known issues: None.
//-----

var iPumpAPI = new function() {
  var self = this;
  //Set before login
  self.url = "ws://127.0.0.1";
  self.port = "7200";
  self.appName = "myApp";
  self.clientId = "1";
  self.version = "4.0";
  self.reconnect = true;
  self.reconnectTimeout = 5000;
  //--==End of init params==--

  self.instance = "0";
  self.serviceEventName = "serviceEvent";
  self.serviceName = "ipumpAppService";
  self.debug = false;
  self.logMsgLength = 400;

  //--==PRIVATE PARAMS AND METHODS==--
  var webSocket;
  var reconnectTimer  = null;
  var relogTimer = null;

  var commandPref = {
    Connect : {
      req: "connectRequestCmd",
      res: "connectRsp",
      pendingRequests:[]
    },
    Login: {
      req: "loginCmd",
      res: "loginRsp",
      pendingRequests:[]
    },
    Logout: {
      req: "logoutCmd",
      res: "logoutRsp",
      pendingRequests:[]
    },
    RequestTransactionState: {
      req: "requestTransactionStateCmd",
      res: "requestTransactionStateRsp",
      resDataObj: "state",
      pendingRequests:[]
    },
    StateChangeEvent: {
      res: "transactionStateChangeEvent",
      resDataObj: "state",
      pendingRequests:[]
    },
    AppConfigChangeEvent: {
      res: "appConfigEvent",
      resDataObj: ["isVisible", "isFocus", "state", "layoutName", "frame"],
      pendingRequests:[]
    },
    SoftKeyEvent: {
      res: "softkeyEvent",
      resDataObj: "softkey",
      pendingRequests:[]
    },
    EnableKeyPad: {
      req: "enableKeyPadCmd",
      res: "enableKeyPadRsp",
      pendingRequests:[]
    },
    KeyPadEvent: {
      res: "keyPadEvent",
      resDataObj: "keyData",
      pendingRequests:[]
    },
    EnableBarcodeScanner: {
      req: "enableBarcodeCmd",
      res: "enableBarcodeRsp",
      pendingRequests:[]
    },
    DisableBarcodeScanner: {
      req: "disableBarcodeCmd",
      res: "disableBarcodeRsp",
      pendingRequests:[]
    },
    BarcodeEvent: {
      res: "barcodeEvent",
      resDataObj: "barcode",
      pendingRequests:[]
    },
    CreateFile: {
      req: "createFileCmd",
      res: "createFileRsp",
      pendingRequests:[]
    },
    ReadFile: {
      req: "readFileCmd",
      res: "readFileRsp",
      resDataObj: "fileData",
      pendingRequests:[]
    },
    UpdateFile: {
      req: "updateFileCmd",
      res: "updateFileRsp",
      pendingRequests:[]
    },
    DeleteFile: {
      req: "deleteFileCmd",
      res: "deleteFileRsp",
      pendingRequests:[]
    },
    GetFileInfo: {
      req: "getFileInfoCmd",
      res: "getFileInfoRsp",
      resDataObj: "fileInfo",
      pendingRequests:[]
    },
    GetDirInfo: {
      req: "getDirInfoCmd",
      res: "getDirInfoRsp",
      resDataObj: "dirInfo",
      pendingRequests:[]
    },
    Print: {
      req: "printCmd",
      res: "printRsp",
      pendingRequests:[]
    },
    SiteConfig: {
      req: "getSiteConfigCmd",
      res: "getSiteConfigRsp",
      resDataObj: "dataKeyValue",
      pendingRequests:[]
    },
    DateTime: {
      req: "getDateTimeCmd",
      res: "getDateTimeRsp",
      resDataObj: ["dateString","timeString"],
      pendingRequests:[]
    },
    LogEvent: {
      req: "logMessageCmd",
      res: "logMessageRsp",
      pendingRequests:[]
    },
    Heartbeat: {
      req: "heartBeatCmd",
      res: "heartBeatRsp",
      pendingRequests:[]
    },
    storeSecure: {
      req: "storeSecureDataCmd",
      res: "storeSecureDataRsp",
      resDataObj: "token",
      pendingRequests:[]
    },
    updateSecure: {
      req: "updateSecureDataCmd",
      res: "updateSecureDataRsp",
      pendingRequests:[]
    },
    retrieveSecure: {
      req: "retrieveSecureDataCmd",
      res: "retrieveSecureDataRsp",
      resDataObj: "buffer",
      pendingRequests:[]
    },
    deleteSecure: {
      req: "deleteSecureDataCmd",
      res: "deleteSecureDataRsp",
      pendingRequests:[]
    },
    appPosSwitch: {
      req: "bufferSwitchCmd",
      res: "bufferSwitchRsp",
      pendingRequests:[]
    },
    activateSkTemplate: {
      req: "activateSkTemplateCmd",
      res: "activateSkTemplateRsp",
      pendingRequests:[]
    },
    registerPub: {
      req: "regPubCmd",
      res: "regPubRsp",
      pendingRequests:[]
    },
    unRegisterPub: {
      req: "unRegPubCmd",
      res: "unRegPubRsp",
      pendingRequests:[]
    },
    publishTopic: {
      req: "pubTopicCmd",
      res: "pubTopicRsp",
      pendingRequests:[]
    },
    registerSub: {
      req: "regSubCmd",
      res: "regSubRsp",
      pendingRequests:[]
    },
    unRegisterSub: {
      req: "unRegSubCmd",
      res: "unRegSubRsp",
      pendingRequests:[]
    },
    subRegEvent:{
      res: "subRegEvent",
      resDataObj: ["topic"],
      pendingRequests:[]
    },
    topicDataEvent: {
      res: "topicDataEvent",
      resDataObj: ["topic", "payload"],
      pendingRequests:[]
    },
    setInFocus: {
      req: "focusAppCmd",
      res: "focusAppRsp",
      pendingRequests:[]
    },
    cloudRegStatusEvent: {
      res: "cloudRegStatusEvent",
      resDataObj: ["status"],
      pendingRequests:[]
    },
    getCloudRegStatus: {
      req: "getCloudRegStatusCmd",
      res: "getCloudRegStatusRsp",
      pendingRequests:[]
    },
    cloudOperationEvent: {
      res: "cloudOperationEvent",
      resDataObj: ["name", "payload"],
      pendingRequests:[]
    },
    sendStatus: {
      req: "sendStatusCmd",
      res: "sendStatusRsp",
      pendingRequests:[]
    }
  };

  //Look for function initMessages
  var messages = {};

  var getMethodByEventName = function(eventName) {
    res = null;
    for (var property in commandPref) {
      if (commandPref[property].res == eventName ) {
        res = property;
        return res;
      }
    }
    return res;
  }

  var apiLog = function(message) {
    if (self.debug) {
      var apiLogString = "iPump JS Adapter [ " + (new Date()).toLocaleTimeString() + " ] :";
      var logLine = apiLogString + " " + message;
      console.log(logLine.substring(0,self.logMsgLength));
    }
  };

  var runMethodQueue = function(method) {
    apiLog(method + ": Queue status: " + JSON.stringify(commandPref[method].pendingRequests));
    if (commandPref[method].pendingRequests.length > 0) {
      apiLog(method + ": There are requests in queue, performing...");
      var request =  commandPref[method].pendingRequests.pop();
      //send msg
      var msg = JSON.parse(request.msgStr);
      smartSendMsg(method,msg,request.successCallBack,request.failCallBack);
    }
    else {
      apiLog(method + ": Queue is empty, no pending requests.");
    }
  };

  var smartSendMsg = function(method,msg,successCallBack,failCallBack) {
    var msgStr = JSON.stringify(msg);
    //Reset method data, queue is loaded with a copy string of msg
    if (msg!=null && msg.theEvent!=null) {
      msg.theEvent.data = {};
    }


    if (commandPref[method].successCallBack!=null || commandPref[method].failCallBack!=null) {
      //wait
      apiLog(method + ": Queueing request...");
      var newRequest = {
        msgStr: msgStr,
        successCallBack: successCallBack,
        failCallBack: failCallBack
      };
      commandPref[method].pendingRequests.push(newRequest);
    }
    else {
      //send msg
      commandPref[method].successCallBack = successCallBack;
      commandPref[method].failCallBack = failCallBack;
      apiLog(method + ": sending request to iPump ---> " + msgStr);
      webSocket.send(msgStr);
    }
  };

  var openSocket = function (msgCallback, openCallback, closeCallback){
    // Ensures only one connection is open at a time
    if(webSocket !== undefined && webSocket.readyState !== WebSocket.CLOSED){
      apiLog("WebSocket is already opened.");
      return;
    }
    // Create a new instance of the websocket
    apiLog("Opening WS on port: " + self.port);
    webSocket = new WebSocket(self.url + ":" + self.port);
    /**
     * Binds functions to the listeners for the websocket.
     */
    webSocket.onopen = function(event) {
      apiLog("Connection opend!");
      openCallback(event);
      event = null;
    };

    webSocket.onmessage = function(event){
      if (event!=null) {
        var jsonData;

        if ( event.data != "" ) {
          apiLog("websocket: Got message from iPump <--- " + event.data);
          jsonData = JSON.parse(event.data);
        }
        else jsonData = event.data;

        switch (jsonData.eventName)
        {
          case commandPref.Connect.res:
            if (jsonData.ok) {
              if (commandPref.Connect.successCallBack!=null)
                commandPref.Connect.successCallBack();
            }
            else {
              if (commandPref.Connect.failCallBack!=null)
                commandPref.Connect.failCallBack(jsonData.errorString);
            }
            break;
          case self.serviceEventName:
            //HANDLE SERVICE EVENTS
            //=====================
            //Learn method
            var method = getMethodByEventName(jsonData.theEvent.eventName);
            //RUN CB BY METHOD
            if (method != null && jsonData.theEvent.data != null) {
              //SUCCESS callback handling
              var successCallBack;
              if (commandPref[method].successCallBack != null) {
                successCallBack = commandPref[method].successCallBack;
              }
              else if (commandPref[method].registeredSuccess != null) {
                successCallBack = commandPref[method].registeredSuccess;
              }

              //FAIL callback handling
              var failCallBack;
              if (commandPref[method].failCallBack != null) {
                failCallBack = commandPref[method].failCallBack;
              }
              else if (commandPref[method].registeredFail != null) {
                failCallBack = commandPref[method].registeredFail;
              }


              if (jsonData.theEvent.data.ok == false) {
                //ERROR
                if (failCallBack != null)
                  failCallBack(jsonData.theEvent.data.errorString);

              }
              else {
                //SUCCESS
                if (successCallBack != null) {
                  //Check if data suppose to be sent in callback
                  var responseDataObj = commandPref[method].resDataObj;
                  if (responseDataObj != null) {
                    //CHECK FOR SPECIAL RULES:
                    if (method == "DateTime") {
                      successCallBack(jsonData.theEvent.data[responseDataObj[0]], jsonData.theEvent.data[responseDataObj[1]]);
                    }

                    else if(responseDataObj.constructor === Array) {
                      var resData = {};

                      for(var i = 0; i < responseDataObj.length; i++)
                      {
                        resData[responseDataObj[i]] = jsonData.theEvent.data[responseDataObj[i]];
                      }

                      successCallBack(resData);

                    }
                    //DEFAULT USE:
                    else successCallBack(jsonData.theEvent.data[responseDataObj]);
                  }
                  else successCallBack();
                }
              }
              //clear call backs and check queue
              commandPref[method].successCallBack = null;
              commandPref[method].failCallBack = null;
              runMethodQueue(method);
            }
            else {
              apiLog("Response method unknown. No callback was fired.");
            }
            //========END==========
            break;

          default:
            apiLog('Default ws message case.');
            if (msgCallback != null) {
              msgCallback(jsonData);
            }
            break;
        }
        //WILL BE REMOVED AFTER ALL SAMPLE APS START WORKING WITH CALLBACKS
        if (msgCallback != null) {
          msgCallback(jsonData);
        }
        jsonData = null;
      }

      event = null;
    };

    webSocket.onclose = function(event){
      apiLog("Connection closed.");
      if ( closeCallback != null ) {
        closeCallback(event);
      }
      event = null;

      if(self.reconnect == true && reconnectTimer==null){
        apiLog("Trying reconnect");
        reconnectTimer = setTimeout(function(){
          apiLog("Opening...");
          openSocket(msgCallback, openCallback, closeCallback);
          reconnectTimer = null;
        }, self.reconnectTimeout);
      }
    };
  };

  var closeSocket = function () {
    webSocket.close();
  };

  var sendMsg = function (jsonMsg) {
    var stringMsg = JSON.stringify(jsonMsg);
    apiLog("NoQueue: sending request to iPump ---> " + stringMsg);
    webSocket.send(stringMsg);
  };

  //--==PUBLIC METHODS==--

  self.initMessages = function() {
    apiLog("Version: " + self.version);
    messages = {
      Connect: {
        eventName: commandPref.Connect.req,
        serviceName: self.serviceName,
        routeInfo: {
          instance: self.instance,
          clientId: self.clientId
        }
      },
      Login: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.Login.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      Logout: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.Logout.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      RequestTransactionState: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.RequestTransactionState.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          }
        }
      },
      EnableKeyPad: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.EnableKeyPad.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      EnableBarcodeScanner: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.EnableBarcodeScanner.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      DisableBarcodeScanner: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.DisableBarcodeScanner.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      CreateFile: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.CreateFile.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      ReadFile: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.ReadFile.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      UpdateFile: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.UpdateFile.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      DeleteFile: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.DeleteFile.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      GetFileInfo: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.GetFileInfo.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      GetDirInfo: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.GetDirInfo.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      Print: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.Print.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      SiteConfig: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.SiteConfig.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      DateTime: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.DateTime.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      LogEvent: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.LogEvent.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      Heartbeat: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.Heartbeat.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      storeSecure: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.storeSecure.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      updateSecure: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.updateSecure.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      retrieveSecure: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.retrieveSecure.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      deleteSecure: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.deleteSecure.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      appPsSwitch: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.appPosSwitch.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      activateSkTemplate: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.activateSkTemplate.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      registerPub: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.registerPub.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      unRegisterPub: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.unRegisterPub.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      publishTopic: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.publishTopic.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      registerSub: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.registerSub.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      unRegisterSub: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.unRegisterSub.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      topicDataEvent: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.topicDataEvent.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      setInFocus:{
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.setInFocus.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      cloudRegStatusEvent: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.cloudRegStatusEvent.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      getCloudRegStatus: {
        eventName: self.serviceEventName,
          theEvent: {
          eventName: commandPref.getCloudRegStatus.req,
            serviceName: self.serviceName,
            routeInfo: {
            instance: self.instance,
              clientId: self.clientId
          },
          data: {}
        }
      },
      cloudOperationEvent: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.cloudOperationEvent.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      },
      sendStatus: {
        eventName: self.serviceEventName,
        theEvent: {
          eventName: commandPref.sendStatus.req,
          serviceName: self.serviceName,
          routeInfo: {
            instance: self.instance,
            clientId: self.clientId
          },
          data: {}
        }
      }
    }
  };

  self.setInFocus = function(successCallBack,failCallBack){
    var msg = messages.setInFocus;
    msg.theEvent.data = {
      appName: self.appName,
      appLoginId: self.clientId
    };
    smartSendMsg("setInFocus",msg,successCallBack,failCallBack);
  }

  self.connect = function (msgCallback, closeCallback, successCallBack, failCallBack) {
    commandPref.Connect.successCallBack = successCallBack;
    commandPref.Connect.failCallBack = failCallBack;
    openSocket(msgCallback, function() {
        sendMsg(messages.Connect);
      }
      ,closeCallback);
  };

  self.login = function (successCallBack, failCallBack) {
    var msg = messages.Login;
    msg.theEvent.data = {
      appName: self.appName,
      loginId: self.clientId
    };
    smartSendMsg("Login",msg,successCallBack,function() {
      failCallBack();

      if(self.reconnect == true && relogTimer==null){
        apiLog("Trying relogin");
        relogTimer = setTimeout(function(){
          apiLog("login...");
          self.login(successCallBack, failCallBack);
          relogTimer = null;
        }, self.reconnectTimeout);
      }
    });
  };

  self.logout = function (successCallBack, failCallBack) {
    var msg = messages.Logout;
    msg.theEvent.data = {
      appName: self.appName,
      loginId: self.clientId
    };
    smartSendMsg("Logout",msg,successCallBack,failCallBack);
  };

  self.requestState = function (successCallBack, failCallBack) {
    smartSendMsg("RequestTransactionState",messages.RequestTransactionState,successCallBack,failCallBack);
  };

  self.registerForAppConfigChange = function(successCallBack, failCallBack) {
    commandPref.AppConfigChangeEvent.registeredSuccess = successCallBack;
    commandPref.AppConfigChangeEvent.registeredFail = failCallBack;
  };

  self.registerForStateChange = function(successCallBack, failCallBack) {
    commandPref.StateChangeEvent.registeredSuccess = successCallBack;
    commandPref.StateChangeEvent.registeredFail = failCallBack;
  };

  self.registerForSoftKeys = function(successCallBack, failCallBack) {
    commandPref.SoftKeyEvent.registeredSuccess = successCallBack;
    commandPref.SoftKeyEvent.registeredFail = failCallBack;
  };

  self.enableKeypad = function (type, timeout, screenName, screenMaxLength, screenTagId, successCallBack, failCallBack) {
    var msg = messages.EnableKeyPad;
    //REGULAR MODE
    msg.theEvent.data = {
      type: type,
      timeout: timeout
    };
    //SECURE MODE
    if ( screenMaxLength!=null && screenName!=null && screenTagId!=null ) {
      msg.theEvent.data.screen = {
        name: screenName,
        maxLength: screenMaxLength,
        tagId: screenTagId
      };
    }
    smartSendMsg("EnableKeyPad",msg,successCallBack,failCallBack);
  };

  self.registerForKeypad = function(successCallBack, failCallBack) {
    commandPref.KeyPadEvent.registeredSuccess = successCallBack;
    commandPref.KeyPadEvent.registeredFail = failCallBack;
  };

  self.enableBarcodeScanner = function(successCallBack, failCallBack) {
    smartSendMsg("EnableBarcodeScanner",messages.EnableBarcodeScanner,successCallBack,failCallBack);
  };

  self.disableBarcodeScanner = function(successCallBack, failCallBack) {
    smartSendMsg("DisableBarcodeScanner",messages.DisableBarcodeScanner,successCallBack,failCallBack);
  };

  self.registerForBarcode = function(successCallBack, failCallBack) {
    commandPref.BarcodeEvent.registeredSuccess = successCallBack;
    commandPref.BarcodeEvent.registeredFail = failCallBack;
  };

  self.createFile = function (fileFullName, data, successCallBack, failCallBack, encoding) {
    var msg = messages.CreateFile;
    if( encoding != null ) {
      msg.theEvent.data = {
        fileName: fileFullName,
        fileData: data,
        encoding: encoding
      };
    }
    else {
      msg.theEvent.data = {
        fileName: fileFullName,
        fileData: data
      };
    }
    smartSendMsg("CreateFile",msg,successCallBack,failCallBack);
  };

  self.readFile = function (fileFullName, successCallBack, failCallBack) {
    var msg = messages.ReadFile;
    msg.theEvent.data ={
      fileName : fileFullName
    };
    smartSendMsg("ReadFile",msg,successCallBack,failCallBack);
  };

  self.updateFile = function (fileFullName, data, successCallBack, failCallBack, encoding) {
    var msg = messages.UpdateFile;
    msg.theEvent.data = {
      fileName : fileFullName,
      fileData: data,
      mode: 'a'
    };
    if (encoding!=null) {
      msg.theEvent.data.encoding = encoding;
    }
    smartSendMsg("UpdateFile",msg,successCallBack,failCallBack);
  };

  self.deleteFile = function (fileFullName, successCallBack, failCallBack) {
    var msg = messages.DeleteFile;
    msg.theEvent.data = {
      fileName : fileFullName
    };
    smartSendMsg("DeleteFile",msg,successCallBack,failCallBack);
  };

  self.getFileInfo = function (fileFullName, successCallBack, failCallBack) {
    var msg = messages.GetFileInfo;
    msg.theEvent.data = {
      fileName: fileFullName
    };
    smartSendMsg("GetFileInfo",msg,successCallBack,failCallBack);
  };

  self.getDirInfo = function (dirName, successCallBack, failCallBack) {
    var msg = messages.GetDirInfo;
    msg.theEvent.data = {
      dirName: dirName
    };
    smartSendMsg("GetDirInfo",msg,successCallBack,failCallBack);
  };

  self.print = function (xml, successCallBack, failCallBack) {
    var msg = messages.Print;
    msg.theEvent.data = {
      printXml: xml
    };
    smartSendMsg("Print",msg,successCallBack,failCallBack);
  };

  self.getSiteConfig = function (successCallBack, failCallBack) {
    var msg = messages.SiteConfig;
    msg.theEvent.data = {
      dataKey: ["siteId", "dispenserId", "side"]
    };
    smartSendMsg("SiteConfig",msg,successCallBack,failCallBack);
  };

  self.sendAppPosSwitchCmd = function (successCallBack, failCallBack) {
    var msg = messages.appPsSwitch;
    msg.theEvent.data = {
      buffer:"POS"
    };
    smartSendMsg("appPosSwitch",msg,successCallBack,failCallBack);
  };

  self.activateSkTemplate = function (skTemplateId, successCallBack, failCallBack ) {
    var msg = messages.activateSkTemplate;
    msg.theEvent.data = {
      skTemplateId: skTemplateId
    };
    smartSendMsg("activateSkTemplate",msg,successCallBack,failCallBack);
  };

  self.getDateTime = function (successCallBack, failCallBack) {
    var msg = messages.DateTime;
    smartSendMsg("DateTime",msg,successCallBack,failCallBack);
  };

  self.log = function(level, message, logType, successCallBack, failCallBack) {
    commandPref.LogEvent.successCallBack = successCallBack;
    commandPref.LogEvent.failCallBack = failCallBack;
    var msg = messages.LogEvent;
    msg.theEvent.data = {
      level: level,
      message: message
    };
    if(logType != "")
      msg.theEvent.data.logType = logType;
    sendMsg(msg);
    msg.theEvent.data = {};
  };

  self.sendHeartbeat  = function(successCallBack, failCallBack) {
    commandPref.Heartbeat.successCallBack = successCallBack;
    commandPref.Heartbeat.failCallBack = failCallBack;
    var msg = messages.Heartbeat;
    sendMsg(msg);
  };

  self.storeSecure = function (data, successCallBack, failCallBack) {
    var msg = messages.storeSecure;
    msg.theEvent.data ={
      buffer : data
    };
    smartSendMsg("storeSecure",msg,successCallBack,failCallBack);
  };

  self.updateSecure = function (token, data, successCallBack, failCallBack) {
    var msg = messages.updateSecure;
    msg.theEvent.data ={
      token : token,
      buffer : data
    };
    smartSendMsg("updateSecure",msg,successCallBack,failCallBack);
  };

  self.retrieveSecure = function (token, successCallBack, failCallBack) {
    var msg = messages.retrieveSecure;
    msg.theEvent.data ={
      token : token
    };
    smartSendMsg("retrieveSecure",msg,successCallBack,failCallBack);
  };

  self.deleteSecure = function (token, successCallBack, failCallBack) {
    var msg = messages.deleteSecure;
    msg.theEvent.data ={
      token : token
    };
    smartSendMsg("deleteSecure",msg,successCallBack,failCallBack);
  };

  self.sendMessage = function(msg) {
    sendMsg(msg);
  };

  //publish application
  self.registerForPublish = function(topic, successCallBack,failCallBack){
    var msg = messages.registerPub;
    msg.theEvent.data ={
      topic : topic
    };
    smartSendMsg("registerPub",msg,successCallBack,failCallBack);
  }

  self.unRegisterForPublish = function(topic, successCallBack,failCallBack){
    var msg = messages.unRegisterPub;
    msg.theEvent.data ={
      topic : topic
    };
    smartSendMsg("unRegisterPub",msg,successCallBack,failCallBack);
  }

  self.publishTopic = function(topic, payload, successCallBack,failCallBack){
    var msg = messages.publishTopic;
    msg.theEvent.data ={
      topic : topic,
      payload: payload
    };
    smartSendMsg("publishTopic",msg,successCallBack,failCallBack);
  }

  self.subRegEvent = function(successCallBack, failCallBack) {
    commandPref.subRegEvent.registeredSuccess = successCallBack;
    commandPref.subRegEvent.registeredFail = failCallBack;
  };

  //subscribe application
  self.registerForSubscribe = function(topic, successCallBack,failCallBack){
    var msg = messages.registerSub;
    msg.theEvent.data ={
      topic : topic
    };
    smartSendMsg("registerSub",msg,successCallBack,failCallBack);
  }

  self.unRegisterForSubscribe = function(topic, successCallBack,failCallBack){
    var msg = messages.unRegisterSub;
    msg.theEvent.data ={
      topic : topic
    };
    smartSendMsg("unRegisterSub",msg,successCallBack,failCallBack);
  }

  self.topicDataEvent = function(successCallBack, failCallBack) {
    commandPref.topicDataEvent.registeredSuccess = successCallBack;
    commandPref.topicDataEvent.registeredFail = failCallBack;
  };

  self.cloudRegStatusEvent = function(successCallBack, failCallBack) {
    commandPref.cloudRegStatusEvent.registeredSuccess = successCallBack;
    commandPref.cloudRegStatusEvent.registeredFail = failCallBack;
  };

  self.getCloudRegStatus = function(successCallBack,failCallBack){
    var msg = messages.getCloudRegStatus;
    smartSendMsg("getCloudRegStatus",msg,successCallBack,failCallBack);
  };

  self.cloudOperationEvent = function(successCallBack, failCallBack) {
    commandPref.cloudOperationEvent.registeredSuccess = successCallBack;
    commandPref.cloudOperationEvent.registeredFail = failCallBack;
  };

  self.sendStatus = function(status, cause, successCallBack,failCallBack){
    var msg = messages.sendStatus;
    msg.theEvent.data ={
      status : status,
      cause: cause
    };
    smartSendMsg("sendStatus",msg,successCallBack,failCallBack);
  }

  //--==INIT==--
  self.initMessages();
};
