export default function authHeader() {
  const user = JSON.parse(localStorage.getItem('user'));

  if (user && user.accessToken) {
    console.log(user.accessToken+"hello");
    return {
      'Authorization': 'Bearer ' + user.accessToken}; // for Spring Boot back-end
  } else {
    console.log("Helo");
    return {};
  }
}
