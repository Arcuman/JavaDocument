import axios from "axios";
import authHeader from "../helpers/AuthHeader";

const DOCUMENTS_API_BASE_URL = "http://localhost:8080/api/v1/documents";

class DocumentService {
  getDocuments() {
    return axios.get(DOCUMENTS_API_BASE_URL, { headers: authHeader() });
  }
  getDocumentById(id) {
    return axios.get(DOCUMENTS_API_BASE_URL + `/${id}`, {
      headers: authHeader(),
    });
  }
  createDocument(document) {
    return axios.get(DOCUMENTS_API_BASE_URL, {
      headers: authHeader(),
    });
  }
}

export default new DocumentService();
