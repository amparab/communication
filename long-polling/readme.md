## How Long Polling Works (Conceptually)

**Client Request:** The client sends an HTTP request to the server.

**Server Waits:** The server holds the request open until either: New data is available, or A timeout occurs.

**Server Response:** The server responds immediately when data is available or after the timeout (some default response).

**Client Reconnects:** After receiving the response, the client immediately sends another request.

This creates the illusion of “real-time” updates without constantly hammering the server.

## Long Polling Client Example

Once the Spring Boot server is running on **`http://localhost:8080/`**, a client can connect using plain JavaScript:

```javascript
function poll() {
    fetch('/poll')
        .then(res => res.text())
        .then(data => {
            console.log("Received:", data);
            poll(); // immediately poll again
        })
        .catch(err => {
            console.error(err);
            setTimeout(poll, 1000); // retry after 1 sec if error
        });
}

poll(); // start polling
```

Then you can trigger the sending of the response by sending a message using the `/send` endpoint.

```curl
curl -X POST -d "amrutap" http://localhost:8080/send
```

You can see that the response is sent back to the client and then immediately the client sends a new request. The new request could be sent on the same TCP connection or a new one, depending on whether the previous connection is still alive (or closed due to network issue, server shutdown or timeout, etc.) and whether keep-alive or HTTP/2 is being used.
