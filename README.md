# Neddy
> Node-like library for Java using Netty.

Requires JDK 8.

## Usage

```
Http.createServer((req, res) -> {
    res.writeHead(200, new HttpHeader(contentType("text/html; charset=UTF-8")));
    res.write("Hello World!");
}).listen(3000);
```
