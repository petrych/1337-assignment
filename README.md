A REST-service for calculating congestion tax.

Prerequisites:
Java >= 11
curl >= 7.82.0

Example of GET request to calculate tax fee for an ordinary car:
```
curl -X GET \
-w "StatusCode: %{response_code}\nContentType: %{content_type}\n" \
--json '{
"vehicle": "car",
"dates": [
"2013-02-08 15:47:00",
"2013-02-08 16:01:00",
"2013-02-08 15:29:00"
]
}' \
http://localhost:8080/congestion-tax-calculator/
```