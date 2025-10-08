## Server sent Events

When we use Server-Sent Events (SSE), the server keeps sending updates to the browser over a single HTTP connection.
It needs the HTTP Header `Content-Type: text/event-stream`

In the server, we are using Reactor which is a Java library for reactive programming. It helps handle data streams efficiently without blocking threads.
In Spring WebFlux, Flux lets the server send continuous data (like live updates or events) to the client as it becomes available, making apps more responsive and efficient.

## Server Sent Events Client Example

Once the Spring Boot server is running on **`ws://localhost:8080`**,  
a client can connect using plain JavaScript:

```javascript
const eventSource = new EventSource("http://localhost:8080/random");

eventSource.onmessage = (event) => {
console.log("Received:", event.data);
};

eventSource.onerror = (err) => {
console.error("Error:", err);
};
```

`EventSource` is a built-in JavaScript object that allows the client (browser) to receive continuous event updates from a server.
EventSource has built-in behaviour that even if the connection breaks (like server restarts, network issue, etc.), the browser automatically retries after a few seconds