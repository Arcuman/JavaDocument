import logo from "./logo.svg";
import "./App.css";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import ListDocumentsComponent from "./Components/ListDocumentsComponent";
import HeaderComponent from "./Components/HeaderComponent";
import FooterComponent from "./Components/FooterComponent";
import CreateDocumentComponent from "./Components/CreateDocumentComponent";
function App() {
  return (
    <div>
      <Router>
        {" "}
        <HeaderComponent />
        <div className="container">
          <Switch>
            <Route path="/" exact component={ListDocumentsComponent}></Route>
            <Route path="/documents" component={ListDocumentsComponent}></Route>
            <Route
              path="/add-document/:id"
              component={CreateDocumentComponent}
            ></Route>
          </Switch>
        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;
