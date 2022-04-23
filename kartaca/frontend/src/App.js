import "./App.css";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Register from "./register";
import Login from "./login";
import "semantic-ui-css/semantic.min.css";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.min.css";
import Mycalendar from "./Mycalendar";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Switch>
          <Route exact path="/">
            <Register />
          </Route>
          <Route path="/login">
            {" "}
            <Login showRegisterLink={true} />
          </Route>
          <Route path="/register">
            {" "}
            <Register />
          </Route>
          <Route path="/Mycalendar">
            {" "}
            <Mycalendar />
          </Route>
          <Route exact path="*">
            404 Not Found
          </Route>
        </Switch>
      </BrowserRouter>
      <ToastContainer />
    </div>
  );
}

export default App;
