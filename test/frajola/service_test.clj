(ns frajola.service-test
  (:require [clojure.test :refer :all]
            [frajola.service :as frajola_service]))

(deftest test-response
  (testing "response"
    (let [response frajola_service/response]
      (is (= response {:status 201 :headers {"Content-Type" "application/json"} :body { :status :ok }})))))
