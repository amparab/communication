## WebSocket Client Example

Once the Spring Boot WebSocket server is running on **`ws://localhost:8080`**,  
a client can connect and communicate using plain JavaScript:

```javascript
const socket = new WebSocket("ws://localhost:8080/chat");

socket.onopen = () => {
    console.log("Connected to server!");
    // You can send messages to the server using .send()
    // socket.send("Hello server!");
};

socket.onmessage = (event) => console.log("Received:", event.data);

socket.onclose = () => console.log("Disconnected!");

socket.onerror = (err) => console.error("Error:", err);
```
WebSocket starts as HTTP, but it uses a handshake upgrade:
```
GET /chat HTTP/1.1
Upgrade: websocket
Connection: Upgrade
```
Once the server replies:
```
HTTP/1.1 101 Switching Protocols
```
the client (ex. browser) flips the TCP socket from HTTP mode → WebSocket mode.

NOTE : The standard WebSocket upgrade mechanism doesn’t work reliably over HTTP/2, so browsers and servers often fall back to HTTP/1.1 for WebSockets.

## Use Cases
- Chat/messaging apps (bi-directional)
- Online games or multiplayer apps 
- Collaborative tools (Google Docs-style editing)
