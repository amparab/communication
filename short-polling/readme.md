## What is Short Polling

In short polling, client repeatedly makes HTTP requests at fixed intervals to check for new data.
Each HTTP request is immediate. Server responds right away with whatever data is currently available (even empty).
Short polling does NOT keep the connection open. Each request is independent.

## Demo

### Server
- We have an endpoint `/poll` in our Spring Boot server.
- This endpoint returns the latest value of a `counter` variable.
- A separate thread continuously increments this counter every 1 second from the moment `ShortPollingService` is initialized.

### Client
- Open your browser and connect to the server at `http://localhost:8080`.
- In the browser console, you can run the following code snippet to start polling:

```bash
function poll() {
    fetch("http://localhost:8080/poll")
        .then(res => res.json()) 
        .then(data => {
            console.log("Received response -> ", data);
        })
        .catch(err => console.error(err));
    setTimeout(poll, 2000);
}

poll();
```
- This function calls the `/poll` endpoint every 2 seconds and logs the latest `counter` value returned by the server.

## Use Cases

- Monitoring dashboards
    - Example: Displaying metrics like CPU usage, memory, or system load.
    - Poll every 1–5 seconds to update charts.
- Background tasks progress
    - Example: File upload progress, report generation, or batch job status.
    - Poll server to get the current completion percentage.
- Simple notifications
    - Example: Email count, task updates, or a message counter.