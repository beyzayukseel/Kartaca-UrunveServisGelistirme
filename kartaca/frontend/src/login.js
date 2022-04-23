import React, { useState } from "react";
import { withRouter, Link } from "react-router-dom/cjs/react-router-dom.min";
import { Button, Container, Form, Grid, Divider } from "semantic-ui-react";
import { toast } from "react-toastify";

const Login = ({ showRegisterLink, history }) => {
  const [usernamePassword, setUsernamePassword] = useState({
    username: "",
    password: "",
  });
  let username;
  let password;

  const [usernamePasswordError, setUsernamePasswordError] = useState({
    username,
    password,
  });

  const handleChange = (event) => {
    event.preventDefault();
    const { name, value } = event.target;
    setUsernamePassword({ ...usernamePassword, [name]: value });
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    const { username, password } = usernamePassword;

    if (username.length < 5) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        username: "please enter username more than 3 characters",
      });
      return;
    }

    if (password.length < 3) {
      setUsernamePasswordError({
        ...usernamePasswordError,
        username: "please enter password more than 3 characters",
      });
      return;
    }

    //alert("form submit edildi");

    fetch("http://localhost:9191/api/user", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ password, username }),
    })
      .then((r) => {
        if (r.ok) {
          return r;
        }

        if (r.status === 401) {
          return Promise.reject(new Error("username yada password hatalı"));
        }

        if (r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("bilinmeyen bir hata oluştu"));
        }
        return Promise.reject(new Error("bilinmeyen hata"));
      })
      .then((r) => r.json())
      .then((response) => {
        localStorage.setItem("token", response.token);
        localStorage.setItem("userId", response.id);
        toast.success(
          `user with token ${localStorage.getItem(
            "token"
          )} , your id: ${localStorage.getItem(
            "userId"
          )}  You are redirecting to dasboard page`
        );

        setTimeout(() => {
          history.push("/MyCalendar");
        }, 3000);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  return (
    <Container>
      <Grid columns="equal">
        <Grid.Column> </Grid.Column>
        <Grid.Column>
          <Form onSubmit={handleSubmit}>
            <Form.Field>
              <label>Username</label>
              <Form.Input
                type="username"
                name="username"
                error={usernamePasswordError.username}
                required
                value={usernamePassword.username}
                onChange={handleChange}
              ></Form.Input>
            </Form.Field>
            <Form.Field>
              <label>Password</label>
              <Form.Input
                type="password"
                name="password"
                error={usernamePasswordError.username}
                required
                value={usernamePassword.password}
                onChange={handleChange}
              ></Form.Input>
            </Form.Field>
            <Form.Field></Form.Field>
            <Button type="submit">Submit</Button>
          </Form>
          <Divider />
          <Link to="/register">Don't you have an account?</Link>
        </Grid.Column>
        <Grid.Column> </Grid.Column>
      </Grid>
    </Container>
  );
};

export default withRouter(Login);
