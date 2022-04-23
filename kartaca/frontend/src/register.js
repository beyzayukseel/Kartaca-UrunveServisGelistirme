import React from "react";
import { Link, withRouter } from "react-router-dom/cjs/react-router-dom.min";
import { toast } from "react-toastify";
import { Button, Container, Divider, Form, Grid } from "semantic-ui-react";

class Register extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      email: "",
      password: "",
      passwordRepeat: "",
      username: "",
    };
  }

  handleSubmit = (event) => {
    event.preventDefault();
    const { email, password, passwordRepeat, username } = this.state;

    if (email.length < 10) {
      this.setState({ emailError: "please enter email more than 3" });
      return;
    }

    if (username.length < 3) {
      this.setState({ nameError: "please enter username more than 3" });
      return;
    }

    if (password.length < 3) {
      this.setState({ passwordError: "please enter password more than 3" });
    }

    if (password !== passwordRepeat) {
      this.setState({
        passwordError: "please make sure that password match",
        passwordRepeatError: "please make sure that password match",
      });
      return;
    } else {
      this.setState({
        passwordError: null,
        passwordRepeatError: null,
      });
    }
    alert("form submit edildi");
    fetch("http://localhost:9191/api/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ email, password, username }),
    })
      .then((r) => {
        if (r.ok) {
          return r;
        }

        if (r.status === 401 || r.status === 403 || r.status === 500) {
          return Promise.reject(new Error("bir hata oluştu"));
        }
        return Promise.reject(new Error("bilinmeyen hata"));
      })
      .then((r) => r.json())
      .then((response) => {
        toast.success(
          `user with id ${response.id} You are redirecting to login page`
        );

        setTimeout(() => {
          this.push("/login");
        }, 3000);
      })
      .catch((e) => {
        toast.error(e.message);
      });
  };

  handleChange = (event) => {
    const { name, value } = event.target;
    this.setState({ [name]: value });
  };

  render() {
    console.log("render tetiklendi");
    return (
      <Container>
        <Grid columns="equal">
          <Grid.Column> </Grid.Column>
          <Grid.Column>
            <Form onSubmit={this.handleSubmit}>
              <Form.Field>
                <label>Email</label>
                <Form.Input
                  type="email"
                  name="email"
                  error={this.state.emailError}
                  required
                  value={this.state.email}
                  onChange={this.handleChange}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Password</label>
                <Form.Input
                  type="password"
                  name="password"
                  error={this.state.passwordError}
                  required
                  value={this.state.password}
                  onChange={(event) => {
                    this.handleChange(event);
                  }}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Repeat Password</label>
                <Form.Input
                  type="password"
                  name="passwordRepeat"
                  error={this.state.passwordRepeatError}
                  required
                  value={this.state.passwordRepeat}
                  onChange={(event) => {
                    this.handleChange(event);
                  }}
                ></Form.Input>
              </Form.Field>
              <Form.Field>
                <label>Username</label>
                <Form.Input
                  type="username"
                  name="username"
                  error={this.state.nameError}
                  required
                  value={this.state.username}
                  onChange={(event) => {
                    this.handleChange(event);
                  }}
                ></Form.Input>
              </Form.Field>

              <Button type="submit">Submit</Button>
            </Form>
            <Divider />
            <Link to="/login">Do you have an acoount?</Link>
          </Grid.Column>
          <Grid.Column> </Grid.Column>
        </Grid>
      </Container>
    );
  }
}

export default withRouter(Register);
