const apiKey = 'TODO-API-KEY';
const symbol = 'ICE';

// change to a const after testing
function fetchStockPrice(apiKey, symbol) {
  const url = `https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=${symbol}&interval=1min&apikey=${apiKey}`;

  fetch(url)
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      const timeSeries = data['Time Series (1min)'];
      const latestTimestamp = Object.keys(timeSeries)[0];
      const latestData = timeSeries[latestTimestamp];
      const price = latestData['4. close'];
      console.log(`The current price of ${symbol} is: $${price}`);
    })
    .catch(error => {
      console.error('Error fetching stock price:', error);
    });
}


fetchStockPrice(apiKey, symbol);
