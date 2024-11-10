(defproject api-fetch-prices "0.1.0-SNAPSHOT"
  :description "A small API that fetches minute-by-minute stock prices from the AlphaVantage API"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
  [org.clojure/clojure "1.11.1"]
  [org.clojure/data.json "2.5.0"]
  [clj-http "3.13.0"]
  ]
  :repl-options {:init-ns api-fetch-prices.core})

