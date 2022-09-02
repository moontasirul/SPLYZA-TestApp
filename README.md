# SPLYZA-TestApp

The primary unit of sale in SPLYZA Teams is the “Team”. A Team is charged for each member on the
Team. There are two types of members; regular “members” and “supporters” (the differences are not
important for this exercise). SPLYZA Teams has controls in place to ensure a Team does not go over
its allotted member and supporter limits. These controls make it necessary to provide Team coaches
and administrators the ability to view the Teams membership limits and status.

## Project Structure

- **Core** - Contains different utilities that can be used by the different modules and base
  structures.

- **Data** - The data layer is responsible for selecting the proper data source for the domain
  layer. It contains the implementations of the repositories declared in the domain layer.

  Components of data layer include:

  * model
    - remote: Defines POJO of network responses.

  * remote: This is responsible for performing network operations eg. defining API endpoints using
    Retrofit.

  * repository: Responsible for exposing data to the domain layer.

- **Di** - Contains dependency injection related file.

- **Domain** - This is the core layer of the application. The domain layer is independent of any
  other layers thus domain business logic can be independent from other layers. This means that
  changes in other layers will have no effect on domain layer eg. screen UI (presentation layer) or
  changing database (data layer) will not result in any code change withing domain layer.

  Components of domain layer include:

  * usecase: They enclose a single action, like getting data from a database or posting to a
    service. They use the repositories to resolve the action they are supposed to do. They usually
    override the operator invoke , so they can be called as a function.

- **Presentation** - The features layer contains components involved in showing information to the
  user. The main part of this layer are the views(activity, fragment) and ViewModels.
