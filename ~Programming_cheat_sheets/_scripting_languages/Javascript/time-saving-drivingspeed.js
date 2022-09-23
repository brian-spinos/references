/**
 * distance in miles, speed in mph
 * result is in hours, now in minutes
 * example:
 *    calculateTimeDiff(409, 70, 80); // 43 minutes
 */
let calculateTimeDiff = (distance, speed1, speed2) => {
  let timming1 = distance / speed1;
  let timming2 = distance / speed2;

  let diff = timming1 - timming2;

  return diff * 60;
}
