/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
var app = {
    // Application Constructor
    initialize: function() {
        this.bindEvents();
    },
    // Bind Event Listeners
    //
    // Bind any events that are required on startup. Common events are:
    // 'load', 'deviceready', 'offline', and 'online'.
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    // deviceready Event Handler
    //
    // The scope of 'this' is the event. In order to call the 'receivedEvent'
    // function, we must explicitly call 'app.receivedEvent(...);'
    onDeviceReady: function() {
        app.receivedEvent('deviceready');

        JAnalytics.setDebugModel(true, function() {
            alert("debug ok");
        }, function(err) {
            alert("debug error:" + err);
        });

    },
    // Update DOM on a Received Event
    receivedEvent: function(id) {
        console.log('Received Event: ' + id);
    }
};

app.initialize();

login = function() {
    JAnalytics.onLoginEvent("QQ", true, { p1: "1", p2: "2" }, function() {
        alert("onLoginEvent ok");
    }, function(err) {
        alert("onLoginEvent error: " + err);
    });
}

register = function() {
    JAnalytics.onRegisterEvent("Wechat", true, { p1: "1", p2: "2" }, function() {
        alert("onRegisterEvent ok");
    }, function(err) {
        alert("onRegisterEvent error: " + err);
    });
}

pageStart = function() {
    JAnalytics.onPageStart("TestPage", function() {
        alert("onPageStart ok");
    }, function(err) {
        alert("onPageStart err: " + err);
    });
}

pageEnd = function() {
    JAnalytics.onPageEnd("TestPage", function() {
        alert("onPageEnd ok");
    }, function(err) {
        alert("onPageEnd err: " + err);
    });
}

count = function() {
    JAnalytics.onCountEvent("CountTest", { p1: "1", p2: "2" }, function() {
        alert("onCountEvent ok");
    }, function(err) {
        alert("onCountEvent err: " + err);
    });
}

calculate = function() {
    JAnalytics.onCalculateEvent("CalculateTest", 1, { p1: "1", p2: "2" }, function() {
        alert("onCalculateEvent ok");
    }, function(err) {
        alert("onCalculateEvent err: " + err);
    });
}

see = function() {
    JAnalytics.onBrowseEvent("BrowseTestId", "BrowseTestName", "BrowseTestType", 3000, { p1: "1", p2: "2" }, function() {
        alert("onBrowseEvent ok");
    }, function(err) {
        alert("onBrowseEvent err: " + err);
    });
}

buy = function() {
    JAnalytics.onPurchaseEvent("purchaseGoodsid", "purchaseGoodsName", 1, true, "CNY", "purchaseGoodsType", 1, { p1: "1", p2: "2" }, function() {
        alert("onPurchaseEvent ok");
    }, function(err) {
        alert("onPurchaseEvent err: " + err);
    });
}