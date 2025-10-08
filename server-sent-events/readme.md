const eventSource = new EventSource("http://localhost:8080/random");

eventSource.onmessage = (event) => {
console.log("Received:", event.data);
};

eventSource.onerror = (err) => {
console.error("Error:", err);
};
