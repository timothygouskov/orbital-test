(ns api-fetch-prices.core
  (:require
    [clj-http.client :as client]
    [clojure.data.json :as json]))

(def API-KEY
  (System/getenv "AV_API_KEY"))

(defn USER-AGENT []
  (System/getenv "AV_USER_AGENT"))

(defn fetch-prices[api-key symbol]
  (let [url (str "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" symbol "&interval=1min&apikey=" API-KEY)
        headers {"User-Agent" USER-AGENT}]
    (client/get url headers)))

(fetch-prices API-KEY "ICE")

