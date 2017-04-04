(ns frajola.config-test
  (:require [clojure.test :refer :all]
            [frajola.config :as frajola_config]))

(deftest test-env
  (testing "when env does not exists"
    (let [ environment (frajola_config/env "PATHi")]
      (is (nil? environment))))

  (testing "get env"
    (let [ environment (frajola_config/env "PATH")]
      (is (not (nil? environment))))))
