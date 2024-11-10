import requests
import json


API_KEY = "TODO-API-KEY"
SYMBOL = "ICE"


def fetch_stock_price(api_key, symbol):
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

  price = fetch_stock_price(api_key, symbol)
  print(f"Here is the current price for {symbol}: ${price}")

