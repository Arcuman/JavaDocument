export default function authHeader() {
  const user = JSON.parse(localStorage.getItem("user"));
  return {
    Authorization:
      "Bearer_eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ3Iiwicm9sZXMiOlsiUk9MRV9VU0VSIiwiUk9MRV9BRE1JTiJdLCJpYXQiOjE2MDYyNDQ2NjUsImV4cCI6MTYwNjI0ODI2NX0._gHb4Jy51xi06DXahgUGeOhBZAgdPxdnTlGD8dp9_Xs",
  };
  if (user && user.token) {
    return { Authorization: "Bearer_" + user.token };
  } else {
    return {};
  }
}
