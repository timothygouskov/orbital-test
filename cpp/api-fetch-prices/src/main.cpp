#include <iostream>
#include <curl/curl.h>
#include <jsoncpp/json/json.h>

using namespace std;

string get_stock_price(const string& api_key, const string& symbol) {
    string url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" + symbol + "&interval=1min&apikey=" + api_key;

    CURL *curl;
    CURLcode res;
    string readBuffer;

    curl = curl_easy_init();
    if(curl) {
        curl_easy_setopt(curl, CURLOPT_URL, url.c_str());
        curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, [](char *ptr, size_t size, size_t nmemb, void *userdata) {
            ((string*)userdata)->append(ptr, size * nmemb);
            return size * nmemb;
        });
        curl_easy_setopt(curl, CURLOPT_WRITEDATA, &readBuffer);
        res = curl_easy_perform(curl);

        curl_easy_cleanup(curl);
    }

    Json::Reader reader;
    Json::Value root;
    reader.parse(readBuffer, root);

    string latest_timestamp = root["Time Series (1min)"].getMemberNames().begin()->asString();
    double price = root["Time Series (1min)"][latest_timestamp]["4. close"].asDouble();

    return to_string(price);
}

int main() {
    string api_key = "TODO-API-KEY";
    string symbol = "ICE";

    string price = get_stock_price(api_key, symbol);
    cout << "The current price of " << symbol << " is: $" << price << endl;

    return 0;
}