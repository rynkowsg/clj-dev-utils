(ns clj-dev-utils.fs
  (:require
   [babashka.fs :as fs]))

(defn create-dir-if-not-exist [dir]
  (if (not (and (fs/exists? dir) (fs/directory? dir)))
    (do
      (fs/create-dir dir)
      :created)
    :already-exist))
#_(create-dir-if-not-exist "/tmp/test")
