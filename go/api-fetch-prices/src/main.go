package main

import (
        "fmt"
        "net/http"
        "encoding/json"
        "io/ioutil"
)

type TimeSeries struct {
        Timestamp string `json:"1. timestamp"`
        Open      string `json:"2. open"`
        High      string `json:"3. high"`
        Low       string `json:"4. low"`
        Close     string `json:"5. close"`
        Volume    string `json:"6. volume"`
}

type AlphaVantageResponse struct {
        Meta struct {
                Symbol string `json:"1. Symbol"`
                Interval string `json:"2. Interval"`
                LastRefreshed string `json:"3. Last Refreshed"`
                OutputSize string `json:"4. Output Size"`
                TimeZone string `json:"5. Time Zone"`
        } `json:"Meta Data" // metadata may not be necessary...`
        TimeSeries map[string]TimeSeries `json:"Time Series (1min)"`
}

func fetchStockPrice(apiKey, symbol string) (string, error) {
        url := fmt.Sprintf("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=%s&interval=1min&apikey=%s", symbol, apiKey)

        resp, err := http.Get(url)
        if err != nil {
                return "", err
        }
        defer resp.Body.Close()

        body, err := ioutil.ReadAll(resp.Body)
        if err != nil {
                return "", err
        }

        var response AlphaVantageResponse
        err = json.Unmarshal(body, &response)
        if err != nil {
                return "", err
        }

        for timestamp, data := range response.TimeSeries {
                return data.Close, nil
        }

        return "", fmt.Errorf("Oops! - No data found for symbol: %s", symbol)
}

func main() {
        apiKey := "TODO - API-KEY"
        symbol := "ICE"

        price, err := fetchStockPrice(apiKey, symbol)
        if err != nil {
                fmt.Println("Oops! - Error fetching price:", err)
                return
        }

        fmt.Printf("Here is the current price for %s is: $%s\n", symbol, price)
}
