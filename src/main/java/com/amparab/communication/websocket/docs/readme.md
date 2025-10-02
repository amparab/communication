## WebSocket Client Example

Once the Spring Boot WebSocket server is running on **`ws://localhost:8080/chat`**,  
a client can connect and communicate using plain JavaScript:

```javascript
const socket = new WebSocket("ws://localhost:8080/chat");

socket.onopen = () => {
    console.log("Connected to server!");
    // You can send messages to the server using .send()
    socket.send("Hello server!");
};

socket.onmessage = (event) => console.log("Received:", event.data);

socket.onclose = () => console.log("Disconnected!");

socket.onerror = (err) => console.error("Error:", err);
