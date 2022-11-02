(ns clj-dev-utils.pdf
  (:import
   (org.apache.pdfbox.multipdf PDFMergerUtility)
   (java.io File)
   (org.apache.pdfbox.io MemoryUsageSetting)))

(defn merge-pdfs
  "merge multiple PDFs into output file"
  [{:keys [output input]}]
  (prn input)
  (let [merger (PDFMergerUtility.)]
    (doseq [f input]
      (.addSource merger (File. ^String f)))
    (.setDestinationFileName merger output)
    (.mergeDocuments merger (MemoryUsageSetting/setupMainMemoryOnly))))
#_ (merge-pdfs
    {:input ["/Users/greg/Desktop/untitled folder/2015_12_Statement.pdf" "/Users/greg/Desktop/untitled folder/2016_01_Statement.pdf"]
     :output "/Users/greg/Desktop/untitled folder/merge2.pdf"})
