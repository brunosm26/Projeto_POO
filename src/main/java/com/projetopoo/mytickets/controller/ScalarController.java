package com.projetopoo.mytickets.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScalarController {

    @GetMapping("/docs")
    public String scalar() {
        return """
                <!doctype html>
                <html>
                <head>
                    <title>Connect Tickets API</title>
                    <meta charset="utf-8" />
                    <meta name="viewport" content="width=device-width, initial-scale=1" />
                </head>
                <body>
                    <script
                        id="api-reference"
                        data-url="/v3/api-docs">
                    </script>
                    <script src="https://cdn.jsdelivr.net/npm/@scalar/api-reference"></script>
                </body>
                </html>
                """;
    }
}
