const webSocket = new WebSocket("ws://192.168.0.128:8080/chat");
console.log(webSocket)
webSocket.onopen = function(message) {
    // 콘솔에 메시지를 남긴다.
    // messageTextArea.value += "Server connect...\n";
    console.log("isTrue?");
    let enterMessageJson = {
        "order": "enter"
    }
    webSocket.send(JSON.stringify(enterMessageJson))
};
// 접속이 끝기는 경우는 브라우저를 닫는 경우이기 떄문에 이 이벤트는 의미가 없음.
webSocket.onclose = function(message) { };
// 에러가 발생하면
webSocket.onerror = function(message) {
    // 콘솔에 메시지를 남긴다.
};
