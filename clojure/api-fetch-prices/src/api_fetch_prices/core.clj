(ns api-fetch-bonds.core
  (:require
    [clj-http.client :as client]
    [clojure.data.json :as json]))

(def API-KEY
  ("TODO-API-KEY")

(def USER-AGENT
  "TODO - some user agent (generate one)")

(defn fetch-stock-price
  [api-key symbol]
  (let [url (str "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" symbol "&interval=1min&apikey=" API-KEY)
        headers {"User-Agent" USER-AGENT}]
    (client/get url headers)))

(fetch-stock-price API-KEY "ICE")

