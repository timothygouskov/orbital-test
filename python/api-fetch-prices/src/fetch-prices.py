import requests
import json
import os

API_KEY = os.environ("AV_API_KEY")
SYMBOL = "ICE"

def fetch_prices(api_key, symbol):
  url = f"https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol={SYMBOL}&interval=1min&apikey={API_KEY}"
  response = requests.get(url)
  response.raise_for_status()
  data = json.loads(response.text)
  time_series = data['Time Series (1min)']
  latest_timestamp, latest_data = next(iter(time_series.items()))
  price = latest_data['4. close']
  return price

if __name__ == "__main__":
  symbol = "ICE"

  price = fetch_prices(api_key, symbol)
  print(f"Here is the current price for {symbol}: ${price}")

