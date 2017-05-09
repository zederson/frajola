(ns frajola.service-test
  (:require [clojure.test :refer :all]
            [frajola.service :as frajola_service]))

(deftest test-response
  (testing "response"
    (let [response frajola_service/response]
      (is (= response {:status 201 :headers {"Content-Type" "application/json"} :body { :status :ok }})))))

(deftest test-delete
  (testing "delete"
    (let [path "/tmp/frajola.txt"]
      (spit path "frajola")
      (frajola_service/delete path)
      (is (false? (.exists (clojure.java.io/as-file path)))) ))

  (testing "when file is not found"
    (let [path "/tmp/frajola.txt"]
      (frajola_service/delete path)
      (is (false? (.exists (clojure.java.io/as-file path)))) )))
