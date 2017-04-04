(ns frajola.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [frajola.service :as frajola_service]
            [frajola.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response)  "{\"message\":\"Bem vindo ao frajola, consulte o html dentro do projeto em: resources/public/index.html\"}"))))

  (testing "POST take-picture"
    (with-redefs [frajola_service/take-picture (fn [] frajola_service/response)]
      (let [response (app (mock/request :post "/take-picture"))]
        (is (= (:status response) 201))
        (is (= (:body response) "{\"status\":\"ok\"}"))
        (is (= (:headers response) {"Content-Type" "application/json"})))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404))
      (is (= (:body response) "Frajola Not Found")))))
