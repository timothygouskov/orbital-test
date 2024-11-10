require 'net/http'
require 'json'

api_key = 'TODO-API-KEY'
symbol = 'ICE'

def fetch_stock_price(api_key, symbol)
  url = URI("https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=#{symbol}&interval=1min&apikey=#{api_key}")
  http = Net::HTTP.new(url.host, url.port)
  request = Net::HTTP::Get.new(url)
  response = http.request(request)
  data = JSON.parse(response.body)
  time_series = data['Time Series (1min)']
  latest_timestamp, latest_data = time_series.first
  price = latest_data['4. close']

  puts "The current price of #{symbol} is: $#{price}"
end

fetch_stock_price(api_key, symbol)
